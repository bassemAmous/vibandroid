package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bof on 31/05/2017.
 */

public class Departement implements Serializable {

    @SerializedName("DepName")
    private String departementName ;


    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }
}
