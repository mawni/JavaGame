import java.util.Random;

/*
project 1 sample solution used as base
*/

public class Direction {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    //these constants are used for switch(){} statements

    public static int getRandom() {
        // Directions are random between 0 and 3.
        Random rand = new Random();
        return rand.nextInt(RIGHT + 1);
    }
}
