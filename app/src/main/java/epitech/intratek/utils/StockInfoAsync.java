package epitech.intratek.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.HashMap;

import epitech.intratek.api.ApiCalls;

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
        params.clear();
        params.put("user", login);
        params.put("token", token);
        String user = network.performGetCall("https://epitech-api.herokuapp.com/user?", params);
        params.clear();
        params.put("token", token);
        String marks = network.performGetCall("https://epitech-api.herokuapp.com/marks?", params);
        String messages = network.performGetCall("https://epitech-api.herokuapp.com/messages?", params);
        editor.putString("MyMessages", messages);
        editor.putString("MyUser", user);
        editor.putString("MyMarks", marks);
        editor.putString("MyInfos", infos);
        editor.apply();
        return true;
    }
}