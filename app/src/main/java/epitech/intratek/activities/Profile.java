package epitech.intratek.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import chazot_a.epitech.intratek.R;
import epitech.intratek.json.MyUser;
import epitech.intratek.json.Student;
import epitech.intratek.utils.HandleImage;
import epitech.intratek.utils.MenuSetUp;

public class Profile extends MenuSetUp {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setUpMenu();

        TextView gpa = (TextView) findViewById(R.id.profile_gpa);
        TextView credits = (TextView) findViewById(R.id.profile_credits);
        TextView logtime = (TextView) findViewById(R.id.profile_logtime);
        TextView login = (TextView) findViewById(R.id.profile_login);
        ImageView image = (ImageView) findViewById(R.id.profile_picture);
        TextView name = (TextView) findViewById(R.id.profiletext_name);
        TextView email = (TextView) findViewById(R.id.profiletext_email);
        TextView ids = (TextView) findViewById(R.id.profiletext_ids);
        TextView location = (TextView) findViewById(R.id.profiletext_location);

        Gson gson = new Gson();
        String infos = preferences.getString("MyInfos", "");
        Student student = gson.fromJson(infos, Student.class);

        String user = preferences.getString("MyUser", "");
        MyUser myUser = gson.fromJson(user, MyUser.class);

        String logTime = student.current.get(0).activeLog.substring(0, student.current.get(0).activeLog.length() - 2) + "h";
        gpa.setText(myUser.gpa.get(0).gpa);
        credits.setText(Integer.toString(myUser.credits));
        logtime.setText(logTime);
        login.setText(student.infos.login);
        new HandleImage.ImageLoadTask(myUser.picture, image).execute();
        name.setText(myUser.title);
        email.setText(myUser.internalEmail);
        String uidgid = myUser.uid + " / " + myUser.gid;
        ids.setText(uidgid);
        location.setText(myUser.location);
    }
}
