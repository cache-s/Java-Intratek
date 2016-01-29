package epitech.intratek.Intents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Yolobitch on 29/01/2016.
 */
public class AlarmReceiver extends BroadcastReceiver
{
    public static final int REQUEST_CODE = 4242;
    public static final String ACTION = "com.codepath.example.servicesdemo.alarm";

    // Triggered by the Alarm periodically (starts the service to run task)
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent i = new Intent(context, NotifSystem.class);
        context.startService(i);
    }

}
