package epitech.intratek.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import chazot_a.epitech.intratek.R;
import epitech.intratek.beans.Mark;

public class GradeDetails extends AppCompatActivity {

    TextView titleModule;
    TextView title;
    TextView date;
    TextView corrector;
    TextView finalNote;
    TextView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_details);

        Intent myIntent = getIntent();

        titleModule = (TextView) findViewById(R.id.titleModule);
        title = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        corrector = (TextView) findViewById(R.id.corrector);
        finalNote = (TextView) findViewById(R.id.grade);
        comment = (TextView) findViewById(R.id.comment);

        titleModule.setText(myIntent.getStringExtra("titleModule"));
        title.setText(myIntent.getStringExtra("title"));
        date.setText(myIntent.getStringExtra("date"));
        corrector.setText(myIntent.getStringExtra("corrector"));
        comment.setText(myIntent.getStringExtra("comment"));
        finalNote.setText("Note : " + Float.toString(myIntent.getFloatExtra("mark", 0)));
    }
}
