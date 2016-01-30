package epitech.intratek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import chazot_a.epitech.intratek.R;
import epitech.intratek.beans.Modules;

/**
 * Created by Dardaxe on 29/01/2016.
 */
public class AdapterModules extends BaseAdapter implements View.OnClickListener {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    private Modules tempValues = null;

    public AdapterModules(Activity a, ArrayList d, Resources resLocal)
    {
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

    public static class ViewHolder{

        public TextView titleModule;
        public TextView credits;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.item_modules, null);

            holder = new ViewHolder();
            holder.titleModule = (TextView) vi.findViewById(R.id.title);
            holder.credits = (TextView) vi.findViewById(R.id.credits);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        if (data.size() <= 0)
            holder.titleModule.setText("No Data");
        else {

            tempValues = null;
            tempValues = (Modules) data.get(position);
            holder.titleModule.setText(tempValues.getTitle());
            holder.credits.setText(tempValues.getCredits());
            vi.setOnClickListener(new OnModuleClickListener(position));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {}

    private class OnModuleClickListener implements View.OnClickListener {
        private int mPosition;

        public OnModuleClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            epitech.intratek.activities.Modules sct = (epitech.intratek.activities.Modules)activity;
            sct.onModuleClick(mPosition);
        }
    }

}
