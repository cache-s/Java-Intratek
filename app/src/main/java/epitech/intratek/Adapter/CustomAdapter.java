package epitech.intratek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import chazot_a.epitech.intratek.R;
import epitech.intratek.beans.Mark;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class CustomAdapter extends BaseAdapter implements View.OnClickListener
{
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Mark tempValues = null;
    int i = 0;

    public CustomAdapter(Activity a, ArrayList d, Resources resLocal) {
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
        public TextView title;
        public TextView year;
        public TextView comment;
        public TextView finalNote;
        public TextView correct;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.tabitem, null);

            holder = new ViewHolder();
            holder.title = (TextView) vi.findViewById(R.id.title);
            holder.year = (TextView) vi.findViewById(R.id.year);
            holder.comment = (TextView) vi.findViewById(R.id.comment);
            holder.finalNote = (TextView) vi.findViewById(R.id.finalNote);
            holder.correct= (TextView) vi.findViewById(R.id.corrector);
            vi.setTag(holder);
        } else
            holder = (ViewHolder)vi.getTag();
        if (data.size() <= 0)
            holder.title.setText("No Data");
        else
        {
            tempValues = null;
            tempValues = (Mark) data.get(position);
            holder.title.setText(tempValues.getTitle());
            holder.year.setText(Integer.toString(tempValues.getScolarYear()));
            holder.comment.setText(tempValues.getComment());
            holder.finalNote.setText(tempValues.getFinalNote());
            holder.correct.setText(tempValues.getCorrect());
            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "========Row Button Clicked=======");
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg) {
            CustomListViewAndroidExample sct = (CustomListViewAndroidExample)activity;
            sct.onItemClick(mPosition);
        }
    }
}