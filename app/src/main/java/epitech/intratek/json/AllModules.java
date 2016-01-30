package epitech.intratek.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dardaxe on 29/01/2016.
 */
public class AllModules
{
    @SerializedName("items")
    public List<AllModule> moduleList;
    @SerializedName("modules")
    public List<AllModule> userModuleList;
}
