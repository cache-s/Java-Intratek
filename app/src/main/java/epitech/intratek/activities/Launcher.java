package epitech.intratek.activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import chazot_a.epitech.intratek.R;
import epitech.intratek.Intents.AlarmReceiver;
import epitech.intratek.utils.StockInfoAsync;

/**
 * Splash screen for Intratek.
 * It lasts for 3 seconds and calls Login if the user is not connected or Home if he is.
 */
public class Launcher extends AppCompatActivity {

    private Runnable        _runnable;
    private StockInfoAsync  stockInfo;
    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);

        mContentView = findViewById(R.id.fullscreen_content);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, 0);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isConnected = preferences.getBoolean("isConnected", false);

        if (isConnected) //Connected
        {
            scheduleAlarm();
            _runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    stockInfo = new StockInfoAsync(preferences.getString("token", ""), preferences.getString("login", ""), Launcher.this, Launcher.this);
                    stockInfo.execute();
                }
            };
        }
        else //Not Connected
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

        Handler handler = new Handler();
        handler.postDelayed(_runnable, 3000);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    public void scheduleAlarm() {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis();
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                10000, pIntent);
    }

    public void cancelAlarm() {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
}
