package epitech.intratek.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import chazot_a.epitech.intratek.R;
import epitech.intratek.json.Message;
import epitech.intratek.json.MyMarks;
import epitech.intratek.json.MyProject;
import epitech.intratek.json.MyUser;
import epitech.intratek.json.Student;
import epitech.intratek.utils.HandleImage;
import epitech.intratek.utils.MenuSetUp;

public class Home extends MenuSetUp
{
    private ImageView imgMsg1;
    private ImageView imgMsg2;
    private TextView msg1;
    private TextView msg2;
    private TextView logTime;
    private TextView credits;
    private TextView gpa;
    private TextView projectTitle1;
    private TextView projectStart1;
    private TextView projectEnd1;
    private TextView projectTitle2;
    private TextView projectStart2;
    private TextView projectEnd2;
    private TextView projectTitle3;
    private TextView projectStart3;
    private TextView projectEnd3;
    private TextView titleGrade1;
    private TextView grade1;
    private TextView titleGrade2;
    private TextView grade2;
    private TextView titleGrade3;
    private TextView grade3;
    private SharedPreferences preferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpMenu();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        gson = new Gson();

        String infos = preferences.getString("MyInfos", "");
        Student student = gson.fromJson(infos, Student.class);

        String user = preferences.getString("MyUser", "");
        MyUser myUser = gson.fromJson(user, MyUser.class);

        gpa = (TextView) findViewById(R.id.gpa);
        logTime = (TextView) findViewById(R.id.logTime);
        credits = (TextView) findViewById(R.id.credits);
        gpa.setText(getResources().getString(R.string.menu_gpa) + myUser.gpa.get(0).gpa);
        logTime.setText(getResources().getString(R.string.menu_logtime) + student.current.get(0).activeLog.substring(0, student.current.get(0).activeLog.length() - 2) + "h");
        credits.setText(getResources().getString(R.string.menu_credits) + Integer.toString(myUser.credits));


        projectTitle1 = (TextView) findViewById(R.id.titleProject1);
        projectStart1 =  (TextView) findViewById(R.id.projectStartDate1);
        projectEnd1 =  (TextView) findViewById(R.id.projectEndDate1);
        projectTitle2 = (TextView) findViewById(R.id.titleProject2);
        projectStart2 =  (TextView) findViewById(R.id.projectStartDate2);
        projectEnd2 =  (TextView) findViewById(R.id.projectEndDate2);
        projectTitle3 = (TextView) findViewById(R.id.titleProject3);
        projectStart3 =  (TextView) findViewById(R.id.projectStartDate3);
        projectEnd3 =  (TextView) findViewById(R.id.projectEndDate3);
        String myProjects = preferences.getString("MyProjects", "");
        Type _type = new TypeToken<List<MyProject>>() {}.getType();
        List<MyProject> projects = gson.fromJson(myProjects, _type);
        projectTitle1.setText(projects.get(0).actiTitle);
        projectTitle2.setText(projects.get(1).actiTitle);
        projectTitle3.setText(projects.get(2).actiTitle);
        projectStart1.setText(projects.get(0).beginActi);
        projectStart2.setText(projects.get(1).beginActi);
        projectStart3.setText(projects.get(2).beginActi);
        projectEnd1.setText(projects.get(0).endActi);
        projectEnd2.setText(projects.get(1).endActi);
        projectEnd3.setText(projects.get(2).endActi);

        titleGrade1 = (TextView) findViewById(R.id.gradeName1);
        grade1 =  (TextView) findViewById(R.id.grade1);
        titleGrade2 = (TextView) findViewById(R.id.gradeName2);
        grade2 =  (TextView) findViewById(R.id.grade2);
        titleGrade3 = (TextView) findViewById(R.id.gradeName3);
        grade3 =  (TextView) findViewById(R.id.grade3);
        String myMarks = preferences.getString("MyMarks", "");
        MyMarks marks = gson.fromJson(myMarks, MyMarks.class);
        titleGrade1.setText(marks.myMark.get(marks.myMark.size()-1).title);
        grade1.setText(Float.toString(marks.myMark.get(marks.myMark.size()-1).finalNote));
        titleGrade2.setText(marks.myMark.get(marks.myMark.size()-2).title);
        grade2.setText(Float.toString(marks.myMark.get(marks.myMark.size()-2).finalNote));
        titleGrade3.setText(marks.myMark.get(marks.myMark.size()-3).title);
        grade3.setText(Float.toString(marks.myMark.get(marks.myMark.size()-3).finalNote));

        imgMsg1 = (ImageView) findViewById(R.id.picMsg1);
        imgMsg2 = (ImageView) findViewById(R.id.picMsg2);
        msg1 = (TextView) findViewById(R.id.msg1);
        msg2 = (TextView) findViewById(R.id.msg2);
        String myMessages = preferences.getString("MyMessages", "");
        Type type = new TypeToken<List<Message>>() {}.getType();
        List<Message> messages = gson.fromJson(myMessages, type);
        if (messages != null && messages.get(0) != null) {
            msg1.setText(Html.fromHtml(messages.get(0).content));
            if (messages.get(0).sender.picture == null)
                imgMsg1.setImageResource(R.drawable.nopicture_profilview);
            else
                new HandleImage.ImageLoadTask(messages.get(0).sender.picture, imgMsg1).execute();
        }
        if (messages != null && messages.get(1) != null) {
            msg2.setText(Html.fromHtml(messages.get(1).content));
            if (messages.get(1).sender.picture == null)
                imgMsg2.setImageResource(R.drawable.nopicture_profilview);
            else
                new HandleImage.ImageLoadTask(messages.get(1).sender.picture, imgMsg2).execute();
        }
    }
}
