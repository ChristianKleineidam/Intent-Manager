package com.LearningLeaflets.IntentApp.Manager.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AllocationRepository {

    private AllocationDao mWordDao;
    private LiveData<List<AppAllocation>> mAllWords;
    private LiveData<List<AppAllocation>> mActiveAllocations;

    AllocationRepository(Application application) {
        AllocationDatabase db = AllocationDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mActiveAllocations = mWordDao.getActiveAllocations();
    }

    LiveData<List<AppAllocation>> getActiveAllocations() {
        return mActiveAllocations;
    }

    public void insert (AppAllocation appAllocation) {
        new insertAsyncTask(mWordDao).execute(appAllocation);
    }

    public void deactivate(AppAllocation appAllocation) {
        appAllocation.setActive(false);
        new updateAsyncTask(mWordDao).execute(appAllocation);

    }

    private static class insertAsyncTask extends AsyncTask<AppAllocation, Void, Void> {

        private AllocationDao mAsyncTaskDao;

        insertAsyncTask(AllocationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AppAllocation... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<AppAllocation, Void, Void> {

        private AllocationDao mAsyncTaskDao;

        updateAsyncTask(AllocationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AppAllocation... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

}