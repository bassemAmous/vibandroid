package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bof on 06/11/2016.
 */

public class DataBase implements Serializable {


    @SerializedName("NAME")
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
