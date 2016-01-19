package chazot_a.epitech.intratek;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import intratek.intratek.R;

/**
 * Launcher activity of Intratek.
 * It's a fullscreen splash screen.
 */
public class Launcher extends AppCompatActivity {

    private Runnable    _runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isConnected = preferences.getBoolean("isConnected", false);

        if (!isConnected) //Not Connected
        {
            _runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Launcher.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            };
        } else //Connected
        {
            _runnable = new Runnable() {
                @Override
                public void run() {
                    //Intent intent = new Intent(Launcher.this, Home.class);
                    //startActivity(intent);
                    finish();
                }
            };
        }

        Handler handler = new Handler();
        handler.postDelayed(_runnable, 3000);
    }
}
