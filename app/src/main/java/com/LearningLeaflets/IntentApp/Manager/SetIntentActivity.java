package com.LearningLeaflets.IntentApp.Manager;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.LearningLeaflets.IntentApp.Manager.data.AllocationViewModel;
import com.LearningLeaflets.IntentApp.Manager.data.AppAllocation;

import java.io.Serializable;

public class SetIntentActivity extends AppCompatActivity {
    private int mNum;
    private AllocationViewModel mAllocationViewModel;
    private AppAllocation previousAppAllocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getIntent().getIntExtra("num", -1);

        setContentView(R.layout.set_intent_layout);
        mAllocationViewModel = ViewModelProviders.of(
                this).get(AllocationViewModel.class);

        final EditText nameEdit = findViewById(R.id.name_edit);
        final EditText intentEdit = findViewById(R.id.intent_edit);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_text = nameEdit.getText().toString();
                String intent_text = intentEdit.getText().toString();
                if (!name_text.equals("") && !intent_text.equals("")) {

                    final AppAllocation appAllocation = new AppAllocation(
                            name_text,
                            intent_text,
                            mNum,
                            true
                    );
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            mAllocationViewModel.insert(appAllocation);
                        }
                    });
                    finish();
                }
            }
        });

        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousAppAllocation != null
                        ) {
                    final AppAllocation appAllocation = previousAppAllocation;

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            mAllocationViewModel.deactivate(appAllocation);
                        }
                    });
                    finish();
                }
                else {
                    nameEdit.setText("");
                    intentEdit.setText("");
                }
            }
        });

        Button testButton = findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = intentEdit.getText().toString();
                if (!text.equals("")) {
                    Intent intent = new Intent(text);
                    startActivity(intent);
                }
            }
        });

        if (getIntent().hasExtra("allocation")) {
            previousAppAllocation = (AppAllocation) getIntent()
                    .getSerializableExtra("allocation");
            nameEdit.setText(previousAppAllocation.getName());
            intentEdit.setText(previousAppAllocation.getIntent());
        }
        else {
            saveButton.setBackgroundResource(R.drawable.blue_button);
            testButton.setBackgroundResource(R.drawable.blue_button);
            deleteButton.setBackgroundResource(R.drawable.blue_button);
            View background = findViewById(R.id.main);
            background.setBackgroundResource(R.drawable.blue_border);
        }
    }
}
