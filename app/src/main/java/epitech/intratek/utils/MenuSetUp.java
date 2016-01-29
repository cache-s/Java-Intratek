package epitech.intratek.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import chazot_a.epitech.intratek.R;
import epitech.intratek.activities.About;
import epitech.intratek.activities.Grades;
import epitech.intratek.activities.Home;
import epitech.intratek.activities.Login;
import epitech.intratek.activities.Messages;
import epitech.intratek.activities.Modules;
import epitech.intratek.activities.Planning;
import epitech.intratek.activities.Profile;
import epitech.intratek.activities.Projects;
import epitech.intratek.json.MyUser;
import epitech.intratek.json.Student;

/**
 * Created by Sebastien on 23/01/2016.
 */
public class MenuSetUp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences preferences;

    public void setUpMenu()
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView gpa = (TextView) header.findViewById(R.id.gpa_text);
        TextView credits = (TextView) header.findViewById(R.id.credits_text);
        TextView logtime = (TextView) header.findViewById(R.id.logtime_text);
        TextView login = (TextView) header.findViewById(R.id.login_text);
        ImageView image = (ImageView) header.findViewById(R.id.profile_image);

        Gson gson = new Gson();

        String infos = preferences.getString("MyInfos", "");
        Student student = gson.fromJson(infos, Student.class);

        String user = preferences.getString("MyUser", "");
        System.out.println("MyInfis = " + infos);
        MyUser myUser = gson.fromJson(user, MyUser.class);

        String logTime = student.current.get(0).activeLog.substring(0, student.current.get(0).activeLog.length() - 2) + "h";
        gpa.setText(myUser.gpa.get(0).gpa);
        credits.setText(Integer.toString(myUser.credits));
        logtime.setText(logTime);
        login.setText(student.infos.login);
        new HandleImage.ImageLoadTask(myUser.picture, image).execute();
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
        getMenuInflater().inflate(R.menu.home, menu);
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
        } else if (id == R.id.nav_modules) {
            startActivity(new Intent(getBaseContext(), Modules.class));
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
        } else if (id == R.id.nav_logout) {
            new AlertDialog.Builder(MenuSetUp.this)
                    .setTitle(getString(R.string.logout_title))
                    .setMessage(getString(R.string.logout_text))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("isConnected", false);
                            editor.apply();

                            Intent intent = new Intent(getBaseContext(), Login.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
