package youssefboufaied.com.betavib.Entity;

/**
 * Created by Oussema on 30/05/2017.
 */
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Point implements Serializable {


    @SerializedName("RMS")
    private Float rms;
    public Float getRms() {
        return rms;
    }
    @SerializedName("PEAK")
    private Float peak;

    @SerializedName("KU")
    private Float ku;

    @SerializedName("CF")
    private Float cf;
    @SerializedName("OVELO")
    private Float ovelo;
    @SerializedName("B1")
    private Float b1;
    @SerializedName("B2")
    private Float b2;
    @SerializedName("B3")
    private Float b3;
    @SerializedName("B4")
    private Float b4;
    @SerializedName("B5")
    private Float b5;
    @SerializedName("B6")
    private Float b6;
    @SerializedName("Speed")
    private Float speed;
    @SerializedName("MeasDate")
    private String measdate;

    public String getMeasdate() {
        return measdate;
    }

    public void setMeasdate(String measdate) {
        this.measdate = measdate;
    }

    public Float getPeak() {
        return peak;
    }


    public void setRms(Float rms) {
        this.rms = rms;
    }


    public void setPeak(Float peak) {
        this.peak = peak;
    }

    public Float getKu() {
        return ku;
    }

    public void setKu(Float ku) {
        this.ku = ku;
    }

    public Float getCf() {
        return cf;
    }

    public void setCf(Float cf) {
        this.cf = cf;
    }

    public Float getOvelo() {
        return ovelo;
    }

    public void setOvelo(Float ovelo) {
        this.ovelo = ovelo;
    }

    public Float getB1() {
        return b1;
    }

    public void setB1(Float b1) {
        this.b1 = b1;
    }

    public Float getB2() {
        return b2;
    }

    public void setB2(Float b2) {
        this.b2 = b2;
    }

    public Float getB3() {
        return b3;
    }

    public void setB3(Float b3) {
        this.b3 = b3;
    }

    public Float getB4() {
        return b4;
    }

    public void setB4(Float b4) {
        this.b4 = b4;
    }

    public Float getB5() {
        return b5;
    }

    public void setB5(Float b5) {
        this.b5 = b5;
    }

    public Float getB6() {
        return b6;
    }

    public void setB6(Float b6) {
        this.b6 = b6;
    }


    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }
}
