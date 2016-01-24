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
import epitech.intratek.Adapter.AdapterProjects;
import epitech.intratek.beans.Project;
import epitech.intratek.json.Message;
import epitech.intratek.json.MyProject;
import epitech.intratek.utils.MenuSetUp;

public class Projects extends MenuSetUp {
    public ArrayList<Project> CustomListViewValuesArr = new ArrayList<>();
    AdapterProjects adapter;
    public Projects CustomListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setUpMenu();
        CustomListView = this;

        Resources res = getResources();
        final ListView list = (ListView)findViewById(R.id.list);

        adapter = new AdapterProjects(CustomListView, CustomListViewValuesArr, res);
        setListData();
        list.setAdapter(adapter);
    }

    public void setListData()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String myProjects = preferences.getString("MyProjects", "");
        Type type = new TypeToken<List<MyProject>>(){}.getType();
        //MyProject[] projects = gson.fromJson(myProjects, MyProject[].class);
        List<MyProject> projects = gson.fromJson(myProjects, type);
        for (int i = 0; i < projects.size(); i++) {
            final Project proj = new Project();

            proj.setTitleModule(projects.get(i).titleModule);
            proj.setTitleProject(projects.get(i).actiTitle);
            CustomListViewValuesArr.add(proj);
        }
    }
}
