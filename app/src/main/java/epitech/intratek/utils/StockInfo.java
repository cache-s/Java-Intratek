package epitech.intratek.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        params.clear();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.add(Calendar.WEEK_OF_YEAR, -3);
        String start = format.format(cal.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.clear(Calendar.MINUTE);
        cal2.clear(Calendar.SECOND);
        cal2.clear(Calendar.MILLISECOND);
        cal2.set(Calendar.DAY_OF_WEEK, cal2.getFirstDayOfWeek());
        cal2.add(Calendar.WEEK_OF_YEAR, 8);
        String end = format.format(cal2.getTime());
        params.put("start", start);
        params.put("end", end);
        params.put("token", token);
        String planning = network.performGetCall("planning?", params);
        editor.putString("MyPlanning", planning);

        editor.apply();
    }
}
