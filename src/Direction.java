import java.util.Random;

public class Direction {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static int getRandom() {
        // Directions are random between 0 and 3.
        Random rand = new Random();
        return rand.nextInt(RIGHT + 1);
    }
}
