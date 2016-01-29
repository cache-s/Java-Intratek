package epitech.intratek.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chazot_a.epitech.intratek.R;
import epitech.intratek.Adapter.AdapterProjects;
import epitech.intratek.api.ApiCalls;
import epitech.intratek.beans.Project;
import epitech.intratek.json.Message;
import epitech.intratek.json.MyProject;
import epitech.intratek.utils.MenuSetUp;

public class Projects extends MenuSetUp {
    public ArrayList<Project> CustomListViewValuesArr = new ArrayList<>();
    AdapterProjects adapter;
    public Projects CustomListView = null;
    private Switch mySwitch;
    private Boolean onlyRegist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        LayoutInflater inflater = this.getLayoutInflater();
        setUpMenu();
        CustomListView = this;

        Resources res = getResources();
        final ListView list = (ListView) findViewById(R.id.list);

        adapter = new AdapterProjects(CustomListView, CustomListViewValuesArr, res);
        setListData();
        LinearLayout listHeaderView = (LinearLayout)inflater.inflate(
                R.layout.layout_project_header, null);
        mySwitch = (Switch) listHeaderView.findViewById(R.id.switchRegistered);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onlyRegist = isChecked;
                CustomListViewValuesArr.clear();
                setListData();
                adapter.notifyDataSetChanged();
            }
        });
        list.setAdapter(adapter);
    }

    public void setListData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String myProjects = preferences.getString("MyProjects", "");
        Type type = new TypeToken<List<MyProject>>() {}.getType();
        List<MyProject> projects = gson.fromJson(myProjects, type);
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("ONLY REGISTER  " + onlyRegist + "REGISTERED " + projects.get(i).registered);
            if (onlyRegist == false || projects.get(i).registered == 1)
            {
                final Project proj = new Project();

                proj.setTitleModule(projects.get(i).titleModule);
                proj.setTitleProject(projects.get(i).actiTitle);
                proj.setScolarYear(projects.get(i).scolarYear);
                proj.setCodeModule(projects.get(i).codeModule);
                proj.setCodeInstance(projects.get(i).codeInstance);
                proj.setCodeActi(projects.get(i).codeActi);
                CustomListViewValuesArr.add(proj);
            }
        }
    }

    public void onProjectClick(int position) {
        Project tempValues = (Project) CustomListViewValuesArr.get(position);
        GetProjectTask mProject = new GetProjectTask(tempValues);
        mProject.execute();
    }

    public class GetProjectTask extends AsyncTask<Void, Void, Boolean> {
        Project tempValues;
        ApiCalls network = ApiCalls.getInstance();
        SharedPreferences preferences;
        Gson gson;

        GetProjectTask(Project _tempValues) {
            tempValues = _tempValues;
        }

        protected Boolean doInBackground(Void... etc) {
            gson = new Gson();
            preferences = PreferenceManager.getDefaultSharedPreferences(Projects.this);
            HashMap<String, String> params = new HashMap<>();
            params.put("token", preferences.getString("token", ""));
            params.put("scolaryear", tempValues.getScolarYear());
            params.put("codemodule", tempValues.getCodeModule());
            params.put("codeinstance", tempValues.getCodeInstance());
            params.put("codeacti", tempValues.getCodeActi());
            String project = network.performGetCall("https://epitech-api.herokuapp.com/project?", params);
            epitech.intratek.json.Project projectDetails = gson.fromJson(project, epitech.intratek.json.Project.class);
            Intent myIntent = new Intent(getBaseContext(), ProjectDetails.class);
            myIntent.putExtra("moduleTitle", projectDetails.module_title);
            myIntent.putExtra("projectTitle", projectDetails.project_title);
            myIntent.putExtra("dateStart", projectDetails.begin);
            myIntent.putExtra("dateEnd", projectDetails.end);
            myIntent.putExtra("description", projectDetails.description);
            myIntent.putExtra("registered", projectDetails.user_project_master);
            myIntent.putExtra("endRegister", projectDetails.end_register);
            myIntent.putExtra("token", preferences.getString("token", ""));
            myIntent.putExtra("scolaryear", projectDetails.scolaryear);
            myIntent.putExtra("codemodule", projectDetails.codemodule);
            myIntent.putExtra("codeinstance", projectDetails.codeinstance);
            myIntent.putExtra("codeacti", projectDetails.codeacti);
            startActivity(myIntent);
            return true;
        }
    }
}

