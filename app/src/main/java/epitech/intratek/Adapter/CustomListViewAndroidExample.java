package epitech.intratek.Adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import chazot_a.epitech.intratek.R;
import epitech.intratek.beans.Mark;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class CustomListViewAndroidExample extends Activity
{
    ListView list;
    CustomAdapter adapter;
    public  CustomListViewAndroidExample CustomListView = null;
    public ArrayList<Mark> CustomListViewValuesArr = new ArrayList<Mark>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view_android_example);

        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        ListView t = ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );

    }

    /****** Function to set data in ArrayList *************/
    public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            final Mark sched = new Mark();

            /******* Firstly take data in model object ******/
            sched.setTitle("Company " + i);
            sched.setTitleModule("image" + i);

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

    }


    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        Mark tempValues = ( Mark ) CustomListViewValuesArr.get(mPosition);


        // SHOW ALERT

        Toast.makeText(CustomListView, "" + tempValues.getTitle() + " titleModule:"+tempValues.getTitleModule(), Toast.LENGTH_LONG).show();
    }
}
