package youssefboufaied.com.betavib.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bof on 28/05/2017.
 */

public class User {
    @SerializedName("success")
    private String success ;


    @SerializedName("message")
    private String message ;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
