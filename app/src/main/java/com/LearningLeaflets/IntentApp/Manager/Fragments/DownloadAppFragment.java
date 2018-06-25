package com.LearningLeaflets.IntentApp.Manager.Fragments;

import android.util.Log;

import com.LearningLeaflets.IntentApp.Manager.R;

public class DownloadAppFragment extends MainFragment {
    private String TAG = "DOWNLOAD_APP";

    protected int getLayout() {
        return R.layout.download_app;
    }

    @Override
    protected void buttonClick() {
        Log.w(TAG, "TODO: Open Google Play for " + num);

    }
}
