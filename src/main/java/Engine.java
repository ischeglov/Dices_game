import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Engine {

    boolean isPlayerGetAnswer = false;
    public static int numbers[] = new int[] {1,2,3,4,5,6};

    public static int throwOnTable() {
        int randomIndex = new Random().nextInt(numbers.length - 1);

        return numbers[randomIndex];
    }

    public void launch() {
        Game.resetResult();

        while (!isPlayerGetAnswer) {
            if (questionStartGame()) {
                start();
            }
        }
    }

    Boolean questionStartGame() {
        System.out.println("Будем играть?");
        String answer = answer();

        return answer.equals("Да");
    }
    String answer() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            return reader.readLine();
        } catch(Exception e) {
            return "";
        }
    }

    void start() {
        int countPlayers = questionCountPlayers();

        if (countPlayers < Game.minCountPlayers || countPlayers > Game.maxCountPlayers) {
            System.out.println("Неверное количество игроков!");
            System.out.println("Минимальное количество игроков: " + Game.minCountPlayers);
            System.out.println("Максимальное количество игроков: " + Game.maxCountPlayers);

            return;
        }

        isPlayerGetAnswer = true;
        initPlayers(countPlayers);
        rollDice();
        checkResult();
        newGame();
    }

    int questionCountPlayers() {
        System.out.println("Сколько игроков будет играть?");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    void newGame() {
        reset();
        launch();
    }

    void initPlayers(int countPlayers) {
        Game.players.clear();

        for (int index = 0; index < countPlayers; index++)  {
            Player player = new Player();
            player.name = Integer.toString(index + 1);
            Game.players.add(player);
        }
    }
    void rollDice() {
        for (int index = 0; index < Game.players.size(); index++)  {
            Game.players.get(index).roll();
        }
    }
    void checkResult() {
        for (int index = 0; index < Game.players.size(); index++) {
            System.out.println("Игрок под номером: " + Game.players.get(index).name + ", бросил кости: " + Game.players.get(index).result());
        }

        Collections.sort(Game.players);

        ArrayList<Player> bestPlayers = new ArrayList<>();
        bestPlayers.add(Game.players.get(0));
        int bestResult = Game.players.get(0).result();
        for (int index = 1; index < Game.players.size(); index++) {
            if (Game.players.get(index).result() == bestResult) {
                bestPlayers.add(Game.players.get(index));
            }
        }

        if (bestPlayers.size() > 1) {
            System.out.println("Ничья");
        }

        for (int index = 0; index < bestPlayers.size(); index++) {
            System.out.println("Победил игрок под номером: " + bestPlayers.get(index).name);
        }
    }

    void reset() {
        isPlayerGetAnswer = false;
    }
}

