package clientsModel;

/**
 * Created by Meseret on 10/27/2017.
 */

public class Commission {
    private String referral_code;
    private String user_name;
    private String total_bit;

    public Commission(String referral_code, String user_name, String total_bit) {
        this.referral_code = referral_code;
        this.user_name = user_name;
        this.total_bit = total_bit;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTotal_bit() {
        return total_bit;
    }

    public void setTotal_bit(String total_bit) {
        this.total_bit = total_bit;
    }
}
