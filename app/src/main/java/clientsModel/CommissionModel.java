package clientsModel;

/**
 * Created by Meseret on 11/3/2017.
 */

public class CommissionModel {
    private String user_name;
    private String total_commision;

    public CommissionModel(String user_name, String total_commision) {
        this.user_name = user_name;
        this.total_commision = total_commision;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTotal_commision() {
        return total_commision;
    }

    public void setTotal_commision(String total_commision) {
        this.total_commision = total_commision;
    }
}
