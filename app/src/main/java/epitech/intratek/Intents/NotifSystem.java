package epitech.intratek.Intents;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import chazot_a.epitech.intratek.R;
import epitech.intratek.activities.Login;
import epitech.intratek.api.ApiCalls;
import epitech.intratek.beans.Message;
import epitech.intratek.json.Planning;
import epitech.intratek.utils.StockInfo;

/**
 * Created by Yolobitch on 29/01/2016.
 */
public class NotifSystem extends IntentService
{
    public NotifSystem()
    {
        super("MyTestService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {

        Gson gson = new Gson();
        ApiCalls api = ApiCalls.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();
        String token = preferences.getString("token", "");
        params.put("token", token);

        if (token == "")
            return;


        String infos = preferences.getString("MyMessages", "");
        Type type = new TypeToken<ArrayList<epitech.intratek.json.Message>>(){}.getType();
        ArrayList<epitech.intratek.json.Message> messages = gson.fromJson(infos, type);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date tempDate = new Date();
        try
        {
            tempDate = format.parse("1970-01-01 00:00:00");
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        int messageId = 0;

        if (messages == null || messages.size() == 0)
            return;
        for (int i = 0; i < messages.size(); i++)
        {
            Date current;
            try
            {
                current = format.parse(messages.get(i).date.toString());
                if (i == 0 || tempDate.compareTo(current) < 0 )
                {
                    tempDate = current;
                    messageId = i;
                    Log.i(messages.get(i).date.toString(), " + " + messages.get(i).title.toString());
                }
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
        }

        Log.i("nombre message : ", "" + messageId);

        String response = api.performGetCall("messages?", params);

        int saveId = 0;
        Log.i("date : ", tempDate.toString());

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            JSONArray jsonResp = new JSONArray(response);
            for (int i = jsonResp.length(); i > 0; i--)
            {
                JSONObject row = jsonResp.getJSONObject(i - 1);
                String data = row.getString("date");
                Date temp = format.parse(data);
                if (temp.compareTo(tempDate) > 0)
                {
                    Log.i("NOTIF : ", row.toString());
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_menu_module)
                                .setContentTitle("Intratek")
                                .setContentText(row.getString("title"));

                    // mId allows you to update the notification later on.
                    mNotificationManager.notify(i, mBuilder.build());
                }
                Log.i("RECU : ", row.toString());
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("MyMessages", String.valueOf(response));
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}