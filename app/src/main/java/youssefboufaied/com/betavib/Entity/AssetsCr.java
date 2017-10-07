package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Oussema on 10/08/2017.
 */

public class AssetsCr {
    @SerializedName("OperationYear")
    public Integer OperationYear;
    @SerializedName("Good")
    public Integer Good;

    @SerializedName("PreAlarm")
    public Integer PreAlarm;

    @SerializedName("Alarm")
    public Integer Alarm;

    @SerializedName("Danger")
    public Integer Danger;

    @SerializedName("NA")
    public Integer NA;


}
