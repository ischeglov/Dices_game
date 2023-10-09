public class Player implements Comparable<Player> {

    int firstRoll = 0;
    int secondRoll = 0;
    String name = "";

    public void roll() {
        firstRoll = Engine.throwOnTable();
        secondRoll = Engine.throwOnTable();
    }

    public void reset() {
        firstRoll = 0;
        secondRoll = 0;
    }

    public int result() {
        return firstRoll + secondRoll;
    }
    @Override public int compareTo(Player Player) {
        int resultOtherPlayer
                = ((Player)Player).result();

        if (this.result() > resultOtherPlayer) {
            return -1;
        } else if (this.result() < resultOtherPlayer) {
            return 1;
        } else {
            return 0;
        }
    }
}

