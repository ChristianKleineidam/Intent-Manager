package com.LearningLeaflets.IntentApp.Manager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.LearningLeaflets.IntentApp.Manager.data.AllocationDao;
import com.LearningLeaflets.IntentApp.Manager.data.AllocationDatabase;
import com.LearningLeaflets.IntentApp.Manager.data.AppAllocation;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.LearningLeaflets.IntentApp.Manager.Fragments.MainFragment.createSetIntentActivityIntent;

public class IntentProcessService extends Service {
    private final static String TAG = "INTENT SERVICE";
    private AllocationDao mWordDao;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final int num = intent.getExtras().getInt("num", -1);
        if (num!=-1){
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    processAppAllocation(num);
                }
            });
        }
        Log.w(TAG, "Received request to start intent " + num);

        return super.onStartCommand(intent, flags, startId);
    }

    private void processAppAllocation(final int num) {
        final Context context = this;
        mWordDao = AllocationDatabase.getDatabase(this).wordDao();
        Single<AppAllocation> single = mWordDao.loadSlotAllocation(num);

        single.subscribe(new SingleObserver<AppAllocation>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(AppAllocation appAllocation) {
                Log.i(TAG, "Load appAllocation " + appAllocation.getName());
                Intent intent = new Intent(appAllocation.getIntent());
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "No active appAllocation at slot " + num);
                Intent intent = createSetIntentActivityIntent(num, context);
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.w(TAG, "onBind");
        return null;
    }
}
