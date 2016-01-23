package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class MyMarks
{
    @SerializedName("notes")
    public List<Mark> myMark;
}
