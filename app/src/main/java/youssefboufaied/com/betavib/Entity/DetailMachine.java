package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Oussema on 06/06/2017.
 */

public class DetailMachine implements Serializable {
    @SerializedName("MachineName")
   String machineName;
    @SerializedName("DepName")
    String    depName;
    @SerializedName("Power")
    Float power;
    @SerializedName("PowerUnit")
    String  PowerUnit;
    @SerializedName("InstallationDate")
    String  installationDate;
    @SerializedName("InputSpeed")
    Double inputSpeed;

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public String getPowerUnit() {
        return PowerUnit;
    }

    public void setPowerUnit(String powerUnit) {
        PowerUnit = powerUnit;
    }

    public Double getInputSpeed() {
        return inputSpeed;
    }

    public void setInputSpeed(Double     inputSpeed) {
        this.inputSpeed = inputSpeed;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    @Override
    public String toString() {
        return "DetailMachine{" +
                "machineName='" + machineName + '\'' +
                ", depName='" + depName + '\'' +
                ", power='" + power + '\'' +
                ", PowerUnit='" + PowerUnit + '\'' +
                ", installationDate='" + installationDate + '\'' +
                ", inputSpeed='" + inputSpeed + '\'' +
                '}';
    }
}
