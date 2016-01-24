package epitech.intratek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import chazot_a.epitech.intratek.R;
import epitech.intratek.activities.Messages;
import epitech.intratek.beans.Mark;
import epitech.intratek.beans.Message;
import epitech.intratek.utils.LoadImage;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class AdapterMessages extends BaseAdapter
{
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Message tempValues = null;
    int i = 0;

    public AdapterMessages(Activity a, ArrayList d, Resources resLocal) {
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

        public TextView title;
        public TextView senderName;
        public ImageView senderPicture;
        public TextView content;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if (convertView == null)
        {
            vi = inflater.inflate(R.layout.item_message, null);

            holder = new ViewHolder();
            holder.title = (TextView) vi.findViewById(R.id.title);
            holder.senderName = (TextView) vi.findViewById(R.id.senderName);
            holder.content = (TextView) vi.findViewById(R.id.content);
            holder.senderPicture = (ImageView) vi.findViewById(R.id.picture);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        if (data.size() <= 0)
            holder.title.setText("No Data");
        else
        {

            tempValues = null;
            tempValues = (Message) data.get(position);
            holder.title.setText(tempValues.getTitle());
            holder.senderName.setText(tempValues.getSenderName());
            holder.content.setText(tempValues.getContent());
            holder.senderPicture.setImageBitmap(null);
            if (tempValues.getSenderPicture() == null)
                holder.senderPicture.setImageResource(R.drawable.nopicture_profilview);
            else
                new LoadImage.ImageLoadTask(tempValues.getSenderPicture(), holder.senderPicture).execute();
        }
        return vi;
    }
}
