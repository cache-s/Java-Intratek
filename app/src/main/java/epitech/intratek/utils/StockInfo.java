package epitech.intratek.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.HashMap;

import epitech.intratek.api.ApiCalls;

/**
 * Created by Dardaxe on 23/01/2016.
 */

public class StockInfo
{
    public void GetAndStockUserInfo(String token, String login, Context context)
    {
        ApiCalls network = ApiCalls.getInstance();
        HashMap<String, String> params = new HashMap<>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        params.put("token", token);
        String infos = network.performPostCall("infos?", params);
        params.clear();
        params.put("user", login);
        params.put("token", token);
        String user = network.performGetCall("user?", params);
        params.clear();
        params.put("token", token);
        String marks = network.performGetCall("marks?", params);
        String messages = network.performGetCall("messages?", params);
        String modules = network.performGetCall("modules?", params);
        String projects = network.performGetCall("projects?", params);
        editor.putString("MyProjects", projects);
        editor.putString("MyModules", modules);
        editor.putString("MyMessages", messages);
        editor.putString("MyUser", user);
        editor.putString("MyMarks", marks);
        editor.putString("MyInfos", infos);
        editor.apply();
    }
}
