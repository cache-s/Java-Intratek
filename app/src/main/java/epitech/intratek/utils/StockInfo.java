package epitech.intratek.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

import epitech.intratek.api.ApiCalls;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class StockInfo
{
    public void  GetAndStockUserInfo(String token, String login, Context context)
    {
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
        editor.putString("MyUser", user);
        editor.putString("MyMarks", marks);
        editor.putString("MyInfos", infos);
        editor.apply();
    }
}
