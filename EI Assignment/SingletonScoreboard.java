class Scoreboard {
    private static Scoreboard instance;
    private int score = 0;

    private Scoreboard() {}

    public static Scoreboard getInstance() {
        if (instance == null) {
            instance = new Scoreboard();
        }
        return instance;
    }

    public void addScore(int points) {
        score += points;
        System.out.println("Current Score: " + score);
    }
}

// Client
public class SingletonScoreboard {
    public static void main(String[] args) {
        Scoreboard s1 = Scoreboard.getInstance();
        Scoreboard s2 = Scoreboard.getInstance();

        s1.addScore(10);
        s2.addScore(20);

        System.out.println("Both are same instance? " + (s1 == s2));
    }
}
