package epitech.intratek.activities;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import chazot_a.epitech.intratek.R;
import epitech.intratek.Adapter.AdapterMessages;
import epitech.intratek.json.Message;
import epitech.intratek.utils.MenuSetUp;

public class Messages extends MenuSetUp {

    public ArrayList<epitech.intratek.beans.Message> CustomListViewValuesArr = new ArrayList<>();
    AdapterMessages adapter;
    public Messages CustomListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        setUpMenu();
        CustomListView = this;

        Resources res = getResources();
        final ListView list = (ListView)findViewById(R.id.list);

        adapter = new AdapterMessages(CustomListView, CustomListViewValuesArr, res);
        setListData();
        list.setAdapter(adapter);
    }

    public void setListData()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String myMessages = preferences.getString("MyMessages", "");
        Type type = new TypeToken<List<Message>>(){}.getType();
        List<Message> messages = gson.fromJson(myMessages, type);
        for (int i = 0; i < messages.size(); i++) {
            final epitech.intratek.beans.Message msg = new epitech.intratek.beans.Message();

            msg.setTitle(messages.get(i).title);
            msg.setContent(messages.get(i).content);
            msg.setSenderName(messages.get(i).sender.senderName);
            msg.setSenderPicture(messages.get(i).sender.picture);
            CustomListViewValuesArr.add(msg);
        }
    }
}
