package epitech.intratek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import chazot_a.epitech.intratek.R;
import epitech.intratek.beans.Message;
import epitech.intratek.beans.Project;
import epitech.intratek.utils.LoadImage;

/**
 * Created by Dardaxe on 24/01/2016.
 */
public class AdapterProjects extends BaseAdapter
{
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Project tempValues = null;
    int i = 0;

    public AdapterProjects(Activity a, ArrayList d, Resources resLocal) {
        activity = a;
        data = d;
        res = resLocal;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView titleModule;
        public TextView titleProject;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.item_projects, null);

            holder = new ViewHolder();
            holder.titleModule = (TextView) vi.findViewById(R.id.titleModule);
            holder.titleProject = (TextView) vi.findViewById(R.id.titleProject);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        if (data.size() <= 0)
            holder.titleModule.setText("No Data");
        else
        {

            tempValues = null;
            tempValues = (Project) data.get(position);
            holder.titleModule.setText(tempValues.getTitleModule());
            holder.titleProject.setText(tempValues.getTitleProject());
    }
        return vi;
    }
}
