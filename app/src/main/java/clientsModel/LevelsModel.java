package clientsModel;

/**
 * Created by Meseret on 11/3/2017.
 */

public class LevelsModel {
    private String user_name;
    private String levels;

    public LevelsModel(String user_name, String levels) {
        this.user_name = user_name;
        this.levels = levels;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }
}
