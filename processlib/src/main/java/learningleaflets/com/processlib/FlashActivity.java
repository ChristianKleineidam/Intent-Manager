package learningleaflets.com.processlib;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class FlashActivity extends AppCompatActivity {

    private String TAG = "MAIN_ACTIVITY";

    protected int getNum(){
        return -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG, "Start onCreate");
        ComponentName cn = startService(createIntent());
        if (cn==null){
            Log.w(TAG, "Intent not received");
            //TODO way to install IntentManager
        }
        else{
            Log.w(TAG, "cna: " + cn.toString());
        }

        finish();
    }

    private Intent createIntent(){
        String address = "com.learningleaflets.START_INTENT";
        Intent intent = new Intent(address);
        intent.putExtra("num", getNum());
        intent.setPackage("com.LearningLeaflets.IntentApp.Manager");
        return intent;
    }
}
