package com.LearningLeaflets.IntentApp.Manager.Fragments;

import com.LearningLeaflets.IntentApp.Manager.R;

import static learningleaflets.com.processlib.Misc.openGooglePlay;

public class DownloadAppFragment extends MainFragment {
    private String TAG = "DOWNLOAD_APP";

    protected int getLayout() {
        return R.layout.download_app;
    }

    @Override
    protected void buttonClick() {
        openGooglePlay( "learningleaflets.com.intent" + num, getContext());
    }
}
