package model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Meseret on 10/24/2017.
 */

public class ResponseMessage {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("token")
    @Expose
    private String token;

    public ResponseMessage(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
