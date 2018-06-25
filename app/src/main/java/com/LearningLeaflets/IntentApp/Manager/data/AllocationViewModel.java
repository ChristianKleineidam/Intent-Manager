package com.LearningLeaflets.IntentApp.Manager.data;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

public class AllocationViewModel extends AndroidViewModel {

    private final String TAG = "ALLOCATION_VIEW_MODEL";
    private AllocationRepository mRepository;

    private LiveData<List<AppAllocation>> mActiveAllocations;


    public AllocationViewModel(Application application) {
        super(application);
        mRepository = new AllocationRepository(application);
        mActiveAllocations = mRepository.getActiveAllocations();
        Log.v(TAG, "getActiveAllocations: " + mActiveAllocations);
        Log.v(TAG, "Initialized AllocationViewModel");
    }

    public LiveData<List<AppAllocation>> getActiveAllocations() { return mActiveAllocations; }

    public void insert(AppAllocation appAllocation) { mRepository.insert(appAllocation); }
    public void deactivate(AppAllocation appAllocation) { mRepository.deactivate(appAllocation); }

}