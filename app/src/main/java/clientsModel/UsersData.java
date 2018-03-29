package clientsModel;

/**
 * Created by Meseret on 10/26/2017.
 */

public class UsersData {
    private String user_name;
    private String email;
    private String referal_code;
    private String bitcoin_address;
    private String amount;

    public UsersData(String user_name, String email, String referal_code, String bitcoin_address, String amount) {
        this.user_name = user_name;
        this.email = email;
        this.referal_code = referal_code;
        this.bitcoin_address = bitcoin_address;
        this.amount = amount;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferal_code() {
        return referal_code;
    }

    public void setReferal_code(String referal_code) {
        this.referal_code = referal_code;
    }

    public String getBitcoin_address() {
        return bitcoin_address;
    }

    public void setBitcoin_address(String bitcoin_address) {
        this.bitcoin_address = bitcoin_address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
