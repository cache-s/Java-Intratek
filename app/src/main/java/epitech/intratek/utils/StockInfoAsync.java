package epitech.intratek.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.HashMap;

import epitech.intratek.activities.Home;
import epitech.intratek.activities.Launcher;
import epitech.intratek.activities.Login;
import epitech.intratek.api.ApiCalls;

/**
 * Created by Dardaxe on 24/01/2016.
 */

public class StockInfoAsync extends AsyncTask<Void, Void, Boolean> {
    private final String token;
    private final String login;
    private final Context context;
    private SharedPreferences preferences;
    private Activity activity;


    public StockInfoAsync(String token, String login, Context context, Activity activity) {
        this.token = token;
        this.login = login;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Void... ect) {
        ApiCalls network = ApiCalls.getInstance();
        HashMap<String, String> params = new HashMap<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        params.put("token", token);
        String infos = network.performPostCall("infos?", params);
        editor.putString("MyInfos", infos);

        params.clear();
        params.put("user", login);
        params.put("token", token);
        String user = network.performGetCall("user?", params);
        if (user.equals(""))
            return false;
        editor.putString("MyUser", user);

        params.clear();
        params.put("token", token);
        String marks = network.performGetCall("marks?", params);
        editor.putString("MyMarks", marks);
        String messages = network.performGetCall("messages?", params);
        editor.putString("MyMessages", messages);
        String modules = network.performGetCall("modules?", params);
        editor.putString("MyModules", modules);
        String projects = network.performGetCall("projects?", params);
        editor.putString("MyProjects", projects);
        editor.apply();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        if (result == false) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isConnected", false);
            editor.apply();
            activity.startActivity(new Intent(activity, Login.class));
        } else
        {
            Intent intent = new Intent(activity, Home.class);
            activity.startActivity(intent);
        }
    }
}