package clientsModel;

/**
 * Created by Meseret on 10/26/2017.
 */

public class Player {
    private String game_player;
    private String game_player2;

    public Player(String game_player, String game_player2) {
        this.game_player = game_player;
        this.game_player2 = game_player2;
    }

    public String getGame_player() {
        return game_player;
    }

    public void setGame_player(String game_player) {
        this.game_player = game_player;
    }

    public String getGame_player2() {
        return game_player2;
    }

    public void setGame_player2(String game_player2) {
        this.game_player2 = game_player2;
    }
}
