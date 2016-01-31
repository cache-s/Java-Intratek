package epitech.intratek.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

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
    private View footerView;
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
        footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_grades_footer, null, false);

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
        for (int i = (marks.myMark.size() - 1) - (nbShowGrades - 11); i > j; --i) {

            final Mark sched = new Mark();

            /******* Firstly take data in model object ******/
            if (i >= 0) {
                sched.setTitle(marks.myMark.get(i).title);
                sched.setFinalNote(marks.myMark.get(i).finalNote);
                sched.setComment(marks.myMark.get(i).comment);
                sched.setCorrect(marks.myMark.get(i).correcteur);
                sched.setScolarYear(marks.myMark.get(i).scolarYear);
                sched.setTitleModule(marks.myMark.get(i).titleModule);
                sched.setDate(marks.myMark.get(i).date);
                /******** Take Model Object in ArrayList **********/
                CustomListViewValuesArr.add(sched);
            }
        }
    }


    /*****************  This function used by adapter ****************/
    public void onGradeClick(int position) {
        Mark tempValues = (Mark) CustomListViewValuesArr.get(position);
        Intent myIntent = new Intent(getBaseContext(), GradeDetails.class);
        myIntent.putExtra("titleModule", tempValues.getTitleModule());
        myIntent.putExtra("title", tempValues.getTitle());
        myIntent.putExtra("date", tempValues.getDate());
        myIntent.putExtra("corrector", tempValues.getCorrect());
        myIntent.putExtra("mark", tempValues.getFinalNote());
        myIntent.putExtra("comment", tempValues.getComment());
        startActivity(myIntent);
    }
}
