package com.LearningLeaflets.IntentApp.Manager.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

import com.LearningLeaflets.IntentApp.Manager.R;
import com.LearningLeaflets.IntentApp.Manager.SetIntentActivity;
import com.LearningLeaflets.IntentApp.Manager.data.AllocationDao;
import com.LearningLeaflets.IntentApp.Manager.data.AllocationViewModel;
import com.LearningLeaflets.IntentApp.Manager.data.AppAllocation;


public class EmptyAllocationFragment  extends MainFragment {

    protected int getLayout(){
        return R.layout.allocator_empty;
    }

    protected void setTexts() {
        TextView text = mainView.findViewById(R.id.text);
        text.setText("Intent for App isn't set " + num);
    }



    @Override
    protected void buttonClick() {
        startActivity(createSetIntentActivityIntent(num, getContext()));
    }
}
