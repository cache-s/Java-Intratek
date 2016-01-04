package intratek.intratek;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Launcher extends AppCompatActivity {

    private Runnable    _runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isConnected = preferences.getBoolean("isConnected", false);
        boolean challengeStatus = preferences.getBoolean("ChallengeStatus", false);

        if (!isConnected)
        {
            _runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    Intent intent = new Intent(Launcher.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            };
        }
        else //Connected
        {
            _runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Launcher.this, Home.class);
                    startActivity(intent);
                    finish();
                }
            };
        }

        Handler handler = new Handler();
        handler.postDelayed(_runnable, 3000);
    }
}
