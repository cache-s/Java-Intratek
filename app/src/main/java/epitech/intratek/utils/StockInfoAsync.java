package epitech.intratek.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.HashMap;

import epitech.intratek.api.ApiCalls;
import epitech.intratek.json.MyModules;

/**
 * Created by Dardaxe on 24/01/2016.
 */

public class StockInfoAsync extends AsyncTask<Void, Void, Boolean> {
    private final String token;
    private final String login;
    private final Context context;

    public StockInfoAsync(String token, String login, Context context) {
        this.token = token;
        this.login = login;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... ect) {
        ApiCalls network = new ApiCalls();
        HashMap<String, String> params = new HashMap<>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        params.put("token", token);
        String infos = network.performPostCall("https://epitech-api.herokuapp.com/infos?", params);
        editor.putString("MyInfos", infos);

        params.clear();
        params.put("user", login);
        params.put("token", token);
        String user = network.performGetCall("https://epitech-api.herokuapp.com/user?", params);
        editor.putString("MyUser", user);

        params.clear();
        params.put("token", token);
        String marks = network.performGetCall("https://epitech-api.herokuapp.com/marks?", params);
        editor.putString("MyMarks", marks);
        String messages = network.performGetCall("https://epitech-api.herokuapp.com/messages?", params);
        editor.putString("MyMessages", messages);
        String modules = network.performGetCall("https://epitech-api.herokuapp.com/modules?", params);
        editor.putString("MyModules", modules);
        String projects = network.performGetCall("https://epitech-api.herokuapp.com/projects?", params);
        editor.putString("MyProjects", projects);
        editor.apply();
        return true;
    }
}