package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by amous on 30/05/17.
 */

public class SelectMachine implements Serializable {
    @SerializedName("MachineName")
    private String MachineName;
    @SerializedName("PointDirName")
    private String PointDirName;
    @SerializedName("PointState")
    private String PointState;
    @SerializedName("PointNumber")
    private String PointNumber;
    @SerializedName("MachineID")
    private String MachineID;
    @SerializedName("Dir")
    private String Dir;

    public SelectMachine(String pointDirName) {
        PointDirName = pointDirName;
        MachineName="mamaa";
        PointState="Gray";
                PointNumber="";
        MachineID="";
                Dir="";
    }

    public String getMachineName() {
        return MachineName;
    }

    public void setMachineName(String machineName) {
        MachineName = machineName;
    }

    public String getPointDirName() {
        return PointDirName;
    }

    public void setPointDirName(String pointDirName) {
        PointDirName = pointDirName;
    }

    public String getPointState() {
        return PointState;
    }

    public void setPointState(String pointState) {
        PointState = pointState;
    }

    public String getPointNumber() {
        return PointNumber;
    }

    public void setPointNumber(String pointNumber) {
        PointNumber = pointNumber;
    }

    public String getMachineID() {
        return MachineID;
    }

    public void setMachineID(String machineID) {
        MachineID = machineID;
    }

    public String getDir() {
        return Dir;
    }

    public void setDir(String dir) {
        Dir = dir;
    }

    @Override
    public String toString() {
        return "SelectMachine{" +
                "MachineName='" + MachineName + '\'' +
                ", PointDirName='" + PointDirName + '\'' +
                ", PointState='" + PointState + '\'' +
                ", PointNumber='" + PointNumber + '\'' +
                ", MachineID='" + MachineID + '\'' +
                ", Dir='" + Dir + '\'' +
                '}';
    }
}
