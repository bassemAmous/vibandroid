package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Struct;

/**
 * Created by bof on 31/05/2017.
 */

public class Plant implements Serializable {
    @SerializedName("PlantName")
    private String PlantName;

    public String getPlantName() {
        return PlantName;
    }

    public void setPlantName(String plantName) {
        this.PlantName = plantName;
    }
}
