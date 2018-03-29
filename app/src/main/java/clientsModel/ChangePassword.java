package clientsModel;

/**
 * Created by Meseret on 11/3/2017.
 */

public class ChangePassword {
    private String new_pass;

    public ChangePassword(String new_pass) {
        this.new_pass = new_pass;
    }

    public String getNew_pass() {
        return new_pass;
    }

    public void setNew_pass(String new_pass) {
        this.new_pass = new_pass;
    }
}
