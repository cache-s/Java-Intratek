package epitech.intratek.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chazot_a.epitech.intratek.R;
import epitech.intratek.Adapter.AdapterModules;
import epitech.intratek.api.ApiCalls;
import epitech.intratek.json.AllModule;
import epitech.intratek.json.AllModules;
import epitech.intratek.json.MyUser;
import epitech.intratek.json.User;
import epitech.intratek.utils.MenuSetUp;

public class Modules extends MenuSetUp {
    public ArrayList<epitech.intratek.beans.Modules> CustomListViewValuesArr = new ArrayList<>();
    public Modules CustomListView = null;
    private AdapterModules adapter;
    private Switch mySwitch;
    ApiCalls network = ApiCalls.getInstance();
    private boolean onlyRegist = false;
    private SharedPreferences preferences;
    private Gson gson;
    private String strModules = "";
    private View mProgressView;
    private Spinner spinner;
    private EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        LayoutInflater inflater = this.getLayoutInflater();
        setUpMenu();
        Resources res = getResources();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        gson = new Gson();
        CustomListView = this;
        mProgressView = findViewById(R.id.login_progress);
        showProgress(true);
        GetAllModules task = new GetAllModules("FR/NAN", "2015");
        task.execute();
        final ListView list = (ListView) findViewById(R.id.list);

        adapter = new AdapterModules(CustomListView, CustomListViewValuesArr, res);
        setListData();
        LinearLayout listHeaderView = (LinearLayout) inflater.inflate(R.layout.layout_modules_header, null);
        year = (EditText) listHeaderView.findViewById(R.id.year);
        mySwitch = (Switch) listHeaderView.findViewById(R.id.switchRegistered);
        spinner = (Spinner) listHeaderView.findViewById(R.id.spinnerBox);

        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showProgress(true);
                GetAllModules task = new GetAllModules(spinner.getSelectedItem().toString(), year.getText().toString());
                task.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onlyRegist = isChecked;
                showProgress(true);
                GetAllModules task = new GetAllModules(spinner.getSelectedItem().toString(), year.getText().toString());
                task.execute();
            }
        });
        year.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                showProgress(true);
                GetAllModules task = new GetAllModules(spinner.getSelectedItem().toString(), year.getText().toString());
                task.execute();
                return false;
            }
        });
        list.addHeaderView(listHeaderView);
        list.setAdapter(adapter);

    }

    public void setListData() {
        AllModules allModules = gson.fromJson(strModules, AllModules.class);
        List<AllModule> moduleList = null;
        if (allModules!= null && onlyRegist == true)
            moduleList = allModules.userModuleList;
        else if (allModules!= null && onlyRegist == false)
            moduleList= allModules.moduleList;
        if (moduleList != null) {
            for (int i = 0; i < moduleList.size(); i++) {
                final epitech.intratek.beans.Modules module = new epitech.intratek.beans.Modules();

                module.setTitle(moduleList.get(i).title);
                module.setCredits(moduleList.get(i).credits);
                CustomListViewValuesArr.add(module);
            }
        }
    }

    public void onModuleClick(int position) {
        epitech.intratek.beans.Modules tempValues = CustomListViewValuesArr.get(position);
        GetModuleTask mModule = new GetModuleTask(tempValues);
        mModule.execute();
    }

    public class GetModuleTask extends AsyncTask<Void, Void, Boolean> {
        epitech.intratek.beans.Modules tempValues;
        SharedPreferences preferences;
        Gson gson;
        GetModuleTask(epitech.intratek.beans.Modules _tempValues) {tempValues = _tempValues;};

        @Override
        protected Boolean doInBackground(Void... etc) {
            gson = new Gson();
            preferences = PreferenceManager.getDefaultSharedPreferences(Modules.this);
            return true;
        }
    }

    public class GetAllModules extends AsyncTask<Void, Void, Boolean> {
        String _location;
        String _year;

        GetAllModules(String location, String year)
        {
            _year = year;
            _location = location;
        }
        protected Boolean doInBackground(Void... etc) {
            String user = preferences.getString("MyUser", "");
            MyUser myUser = gson.fromJson(user, MyUser.class);
            HashMap<String, String> param = new HashMap<>();
            param.put("token", preferences.getString("token", ""));
            param.put("scolaryear", _year);
            param.put("location", _location);
            param.put("course", myUser.courseCode);
            if (onlyRegist == true)
                strModules = network.performGetCall("modules?", param);
            else
                strModules = network.performGetCall("allmodules?", param);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            CustomListViewValuesArr.clear();
            setListData();
            adapter.notifyDataSetChanged();
            showProgress(false);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
