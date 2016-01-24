package epitech.intratek.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import chazot_a.epitech.intratek.R;
import epitech.intratek.utils.MenuSetUp;

public class Disconnect extends MenuSetUp {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disconnect);
        setUpMenu();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isConnected", false);
        editor.apply();

        Intent intent = new Intent(Disconnect.this, Login.class);
        startActivity(intent);
        finish();
    }
}
