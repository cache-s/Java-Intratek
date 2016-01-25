package epitech.intratek.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import chazot_a.epitech.intratek.R;

public class ProjectDetails extends AppCompatActivity {

    TextView moduleTitle;
    TextView projectTitle;
    TextView dateStart;
    TextView dateEnd;
    TextView description;
    ImageView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        Intent myItent = getIntent();

        moduleTitle = (TextView) findViewById(R.id.moduleTitle);
        projectTitle = (TextView) findViewById(R.id.projectTitle);
        dateStart = (TextView) findViewById(R.id.dateStart);
        dateEnd = (TextView) findViewById(R.id.dateEnd);
        description = (TextView) findViewById(R.id.description);
        register = (ImageView) findViewById(R.id.register);

        moduleTitle.setText(myItent.getStringExtra("moduleTitle"));
        projectTitle.setText(myItent.getStringExtra("projectTitle"));
        dateStart.setText(myItent.getStringExtra("dateStart"));
        dateEnd.setText(myItent.getStringExtra("dateEnd"));
        description.setText(myItent.getStringExtra("description"));
        register.setImageResource(R.drawable.ic_menu_camera);

    }
}
