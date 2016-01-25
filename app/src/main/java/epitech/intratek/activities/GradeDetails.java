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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_details);

        Intent myIntent = getIntent(); // gets the previously created intent
        Mark tempValues = new Mark();
        tempValues.setTitleModule(myIntent.getStringExtra("titleModule"));
        tempValues.setTitle(myIntent.getStringExtra("title"));
        tempValues.setDate(myIntent.getStringExtra("date"));
        tempValues.setCorrect(myIntent.getStringExtra("corrector"));
        tempValues.setFinalNote(myIntent.getFloatExtra("mark", 0));
        tempValues.setComment(myIntent.getStringExtra("comment"));
        TextView titleModule = (TextView) findViewById(R.id.titleModule);
        TextView title = (TextView) findViewById(R.id.title);
        TextView date = (TextView) findViewById(R.id.date);
        TextView corrector = (TextView) findViewById(R.id.corrector);
        TextView finalNote = (TextView) findViewById(R.id.grade);
        TextView comment = (TextView) findViewById(R.id.comment);
        titleModule.setText(myIntent.getStringExtra("titleModule"));
        title.setText(myIntent.getStringExtra("title"));
        date.setText(myIntent.getStringExtra("date"));
        corrector.setText(myIntent.getStringExtra("corrector"));
        comment.setText(myIntent.getStringExtra("comment"));
        finalNote.setText("Note : " + Float.toString(tempValues.getFinalNote()));
    }
}
