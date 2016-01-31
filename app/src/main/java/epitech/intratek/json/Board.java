package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Board
{
    @SerializedName("projets")
    public List<Projects> projects;
    public List<Notes> notes;
    public List<Susies> susies;
    public List<Activities> activities;
    public List<Modules> modules;
    public List<Stages> stages;
    public List<Tickets> tickets;
}
