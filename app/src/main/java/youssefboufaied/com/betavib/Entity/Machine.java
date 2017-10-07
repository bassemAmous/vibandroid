package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by amous on 29/05/17.
 */

public class Machine implements Serializable {

    @SerializedName("DepName")
    private String depName;
    @SerializedName("StateID")
    private String StateID;
    @SerializedName("SiteName")
    private String SiteName;
    @SerializedName("PlantName")
    private String PlantName;
    @SerializedName("MachineName")
    private String MachineName;
    @SerializedName("MachineState")
    private String MachineState;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getStateID() {
        return StateID;
    }

    public void setStateID(String stateID) {
        StateID = stateID;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }

    public String getPlantName() {
        return PlantName;
    }

    public void setPlantName(String plantName) {
        PlantName = plantName;
    }

    public String getMachineName() {
        return MachineName;
    }

    public void setMachineName(String machineName) {
        MachineName = machineName;
    }

    public String getMachineState() {
        return MachineState;
    }

    public void setMachineState(String machineState) {
        MachineState = machineState;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "depName='" + depName + '\'' +
                ", StateID='" + StateID + '\'' +
                ", SiteName='" + SiteName + '\'' +
                ", PlantName='" + PlantName + '\'' +
                ", MachineName='" + MachineName + '\'' +
                ", MachineState='" + MachineState + '\'' +
                '}';
    }
}
