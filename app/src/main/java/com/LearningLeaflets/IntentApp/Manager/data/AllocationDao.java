package com.LearningLeaflets.IntentApp.Manager.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface AllocationDao {
    @Insert
    void insert(AppAllocation allocation);

    @Update
    void update(AppAllocation allocation);

    @Query("DELETE FROM app_allocation")
    void deleteAll();

    @Query("SELECT * from app_allocation WHERE active=0 " +
            "and name LIKE :name_excerpt " +
            "and intent LIKE :intent_excerpt " +
            "ORDER BY id ASC")
    LiveData<List<AppAllocation>> getInactiveAllocations(
            String name_excerpt,
            String intent_excerpt);

    @Query("SELECT * from app_allocation  WHERE active=1 ORDER BY slot ASC")
    LiveData<List<AppAllocation>> getActiveAllocations();

    @Query("SELECT * from app_allocation  WHERE active=1 and slot=:num ORDER BY slot ASC")
    AppAllocation getAllocationForSlot(int num);

    @Query("SELECT * from app_allocation  WHERE active=1 and slot=:slot LIMIT 1")
    Single<AppAllocation> loadSlotAllocation(int slot);
}
