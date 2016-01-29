package epitech.intratek.Intents;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

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
        Log.i("MyTestService", "Service running");

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


        for (int i = 0; i < messages.size(); i++)
        {
            //if (messages)
        }
        String response = api.performGetCall("messages?", params);
        String savedMessages = preferences.getString("MyMessages", "");

    }
}