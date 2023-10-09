import java.util.ArrayList;

public class Game {

    public static ArrayList<Player> players = new ArrayList<>();
    public static Engine engine = new Engine();
    public static final int minCountPlayers = 2;
    public static final int maxCountPlayers = 8;

    public static void main(String[] args) {
        engine.launch();
    }
    public static void resetResult() {
        for (int index = 0; index < players.size(); index++) {
            players.get(index).reset();
        }
    }
}
