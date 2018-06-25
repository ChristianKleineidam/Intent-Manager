package com.LearningLeaflets.IntentApp.Manager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.LearningLeaflets.IntentApp.Manager.Fragments.DownloadAppFragment;
import com.LearningLeaflets.IntentApp.Manager.Fragments.EmptyAllocationFragment;
import com.LearningLeaflets.IntentApp.Manager.Fragments.FullAllocationFragment;
import com.LearningLeaflets.IntentApp.Manager.data.AllocationViewModel;
import com.LearningLeaflets.IntentApp.Manager.data.AppAllocation;

import java.util.List;

public class ManagerOverview extends AppCompatActivity {

    private AllocationViewModel mAllocationViewModel;
    private final static String TAG = "MANAGER_OVERVIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_overview);
        mFragmentManager = getSupportFragmentManager();
        mAllocationViewModel = ViewModelProviders.of(this).get(AllocationViewModel.class);

        mAllocationViewModel.getActiveAllocations().observe(this, new Observer<List<AppAllocation>>() {
            @Override
            public void onChanged(@Nullable final List<AppAllocation> words) {
                for (int i = 1; i < 6; i++) {
                    setFrame(i, words);
                }
            }
        });

        Log.w(TAG, "Package: " + getPackageName());
    }


    private int getFrameLayoutId(int num) {
        switch (num) {
            case 1:
                return R.id.frame1;
            case 2:
                return R.id.frame2;
            case 3:
                return R.id.frame3;
            case 4:
                return R.id.frame4;
            case 5:
                return R.id.frame5;

            default:
                throw new IllegalArgumentException("Frame doesn't exist: " + num);
        }
    }

    protected FragmentManager mFragmentManager;

    private boolean app_with_num_installed(int num) {
        return isPackageInstalled("learningleaflets.com.intent" + num,
                getPackageManager());
    }

    private void load_fragment_into_slot(int num, Fragment frag, Bundle bundle) {
        frag.setArguments(bundle);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(getFrameLayoutId(num), frag);
        ft.commit();
    }

    private void setFrame(int num, List<AppAllocation> allocationList) {
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);

        if (!app_with_num_installed(num)) {
            load_fragment_into_slot(num, new DownloadAppFragment(), bundle);
            return;
        }
        AppAllocation appAllocation = slot_has_active_allocation(num, allocationList);
        if (appAllocation != null) {
            bundle.putSerializable("allocation", appAllocation);
            load_fragment_into_slot(num, new FullAllocationFragment(), bundle);
        }
        else {
            load_fragment_into_slot(num, new EmptyAllocationFragment(), bundle);
        }
    }

    private AppAllocation slot_has_active_allocation(int num, List<AppAllocation> words) {
        for (AppAllocation appAllocation : words) {
            if (appAllocation.getSlot() == num) {
                return appAllocation;
            }
        }
        return null;
    }

    private boolean isPackageInstalled(String package_name, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(package_name, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
