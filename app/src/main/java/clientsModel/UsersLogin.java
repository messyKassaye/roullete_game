package clientsModel;

/**
 * Created by Meseret on 10/26/2017.
 */

public class UsersLogin {
    private String email;
    private String password;

    public UsersLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
