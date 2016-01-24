package epitech.intratek.activities;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import chazot_a.epitech.intratek.R;
import epitech.intratek.Adapter.CustomAdapter;
import epitech.intratek.beans.Mark;
import epitech.intratek.json.MyMarks;
import epitech.intratek.utils.MenuSetUp;

public class Grades extends MenuSetUp implements NavigationView.OnNavigationItemSelectedListener {
    ListView list;
    CustomAdapter adapter;
    public  Grades CustomListView = null;
    public ArrayList<Mark> CustomListViewValuesArr = new ArrayList<>();
    private int nbShowGrades = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        setUpMenu();
        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        final ListView list = ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )
        View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_grades_footer, null, false);

        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter(adapter);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbShowGrades += 10;
                setListData();
                adapter.notifyDataSetChanged();
            }
        });
        list.addFooterView(footerView);

    }

        /****** Function to set data in ArrayList *************/
    public void setListData()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String myMarks = preferences.getString("MyMarks", "");
        MyMarks marks = gson.fromJson(myMarks, MyMarks.class);
        int j = marks.myMark.size() - nbShowGrades;
        for (int i = (marks.myMark.size() - 1); i > j; --i) {

            final Mark sched = new Mark();

            /******* Firstly take data in model object ******/
            sched.setTitle(marks.myMark.get(i).title);
            sched.setFinalNote(marks.myMark.get(i).finalNote);
            sched.setComment(marks.myMark.get(i).comment);
            sched.setCorrect(marks.myMark.get(i).correcteur);
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }
    }


    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        Mark tempValues = ( Mark ) CustomListViewValuesArr.get(mPosition);


        // SHOW ALERT

        Toast.makeText(CustomListView, "title:" + tempValues.getTitle() + " titleModule:" + tempValues.getTitleModule(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(getBaseContext(), Home.class));
            finish();
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(getBaseContext(), Profile.class));
            finish();
        } else if (id == R.id.nav_activities) {
            startActivity(new Intent(getBaseContext(), Activities.class));
            finish();
        } else if (id == R.id.nav_planning) {
            startActivity(new Intent(getBaseContext(), Planning.class));
            finish();
        } else if (id == R.id.nav_grades) {
            startActivity(new Intent(getBaseContext(), Grades.class));
            finish();
        } else if (id == R.id.nav_projects) {
            startActivity(new Intent(getBaseContext(), Projects.class));
            finish();
        } else if (id == R.id.nav_messages) {
            startActivity(new Intent(getBaseContext(), Messages.class));
            finish();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(getBaseContext(), About.class));
            finish();
        } else if (id == R.id.nav_disconnect) {
            startActivity(new Intent(getBaseContext(), Disconnect.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
