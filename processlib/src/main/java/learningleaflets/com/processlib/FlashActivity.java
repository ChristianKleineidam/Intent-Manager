package learningleaflets.com.processlib;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static learningleaflets.com.processlib.Misc.isPackageInstalled;
import static learningleaflets.com.processlib.Misc.openGooglePlay;


public class FlashActivity extends AppCompatActivity {

    private String TAG = "MAIN_ACTIVITY";

    protected int getNum(){
        return -1;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.w(TAG, "Start onCreate: " + getPackageName());

        if (isPackageInstalled("com.LearningLeaflets.IntentApp.Manager",
                getPackageManager())) {

            ComponentName cn = startService(createIntent());
            if (cn == null) {
                Log.w(TAG, "Intent not received");
            }
            else {
                Log.w(TAG, "cna: " + cn.toString());
            }

            finish();
        }
        else{
            displayDownloadButton(savedInstanceState);
        }
    }

    private void displayDownloadButton(Bundle savedInstanceState){
        setContentView(R.layout.please_download);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGooglePlay(
                        "com.LearningLeaflets.IntentApp.Manager",
                        FlashActivity.this);
            }
        });
    }



    private Intent createIntent(){
        String address = "com.learningleaflets.START_INTENT";
        Intent intent = new Intent(address);
        intent.putExtra("num", getNum());
        intent.setPackage("com.LearningLeaflets.IntentApp.Manager");
        return intent;
    }

}
