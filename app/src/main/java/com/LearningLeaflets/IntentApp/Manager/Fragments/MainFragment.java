package com.LearningLeaflets.IntentApp.Manager.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.LearningLeaflets.IntentApp.Manager.R;
import com.LearningLeaflets.IntentApp.Manager.SetIntentActivity;
import com.LearningLeaflets.IntentApp.Manager.data.AllocationViewModel;

public class MainFragment extends Fragment {
    protected View mainView;
    protected int num = -1;
    private String TAG = "MAIN FRAGMENT";
    protected Bundle arguments;
    protected AllocationViewModel mAllocationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(getLayout(), container, false);
        mAllocationViewModel = ViewModelProviders.of(
                this).get(AllocationViewModel.class);

        num = getArguments().getInt("num");
        Log.v(TAG, "Create FragmentView" + num);
        setNum();

        setTexts();
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });

        return mainView;
    }

    protected void buttonClick(){

    }

    protected void setTexts() {
        TextView text = mainView.findViewById(R.id.text);
        text.setText("You need to download another app for this feature " + num);
    }

    protected void setNum(){
        TextView numText = mainView.findViewById(R.id.num);
        numText.setText(Integer.toString(num));
    }

    protected int getLayout(){
        throw new IllegalStateException("getLayout in MainFragment has to be overwritten");
    }

    public static Intent createSetIntentActivityIntent(int num, Context context){
        Intent intent = new Intent();
        intent.setClass(context, SetIntentActivity.class);
        intent.putExtra("num", num);
        return intent;
    }
}
