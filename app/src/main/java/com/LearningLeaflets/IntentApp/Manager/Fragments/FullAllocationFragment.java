package com.LearningLeaflets.IntentApp.Manager.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.LearningLeaflets.IntentApp.Manager.R;
import com.LearningLeaflets.IntentApp.Manager.data.AllocationViewModel;
import com.LearningLeaflets.IntentApp.Manager.data.AppAllocation;

public class FullAllocationFragment extends MainFragment {
    protected int getLayout(){
        return R.layout.allocator_full;
    }

    protected void setTexts() {
        TextView text = mainView.findViewById(R.id.text);
        final AppAllocation appAllocation = getAppAllocation();
        text.setText(String.format("%s\n %s",
                        appAllocation.getName(),
                        appAllocation.getIntent()));

        View circle = mainView.findViewById(R.id.num);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appAllocation.getIntent());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void buttonClick() {
        Intent intent = createSetIntentActivityIntent(num, getContext());
        intent.putExtra("allocation", getAppAllocation());
        startActivity(intent);
    }

    private AppAllocation getAppAllocation(){
        assert getArguments() != null;
        return (AppAllocation) getArguments().getSerializable("allocation");
    }
}
