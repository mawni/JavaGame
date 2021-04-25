import java.util.Random;

/*
project 1 sample solution used as base
*/

/**
 * Used to keep a single source of truth for the directions and the numbers that correspond to them.
 * Is not intended to be used as an instanced object.
 */
public class Direction {
    /**
     * Number that corresponds to direction of up.
     */
    public static final int UP = 0;
    /**
     * Number that corresponds to direction of down.
     */
    public static final int DOWN = 1;
    /**
     * Number that corresponds to direction of left.
     */
    public static final int LEFT = 2;
    /**
     * Number that corresponds to direction of right.
     */
    public static final int RIGHT = 3;
    //these constants are used for switch(){} statements

    /**
     * Used to get a random number within the realm of the directions and their respective numbers 0,1,2,3.
     * @return A random integer between 0 and 3 (inclusive).
     */
    public static int getRandom() {
        // Directions are random between 0 and 3.
        Random rand = new Random();
        return rand.nextInt(RIGHT + 1);
    }

    /**
     * Used to take a direction, and give back a 90 degrees anti-clockwise rotation.
     * @param direction An integer that is either: 0,1,2,3. Those numbers correspond to up, down, left, right, respectively.
     * @return The 90 degree anti-clockwise rotation of a given direction.
     */
    public static int antiClockwise90(int direction){
        //takes in a direction and rotates it counter clockwise 90 degrees
        switch (direction){
            case Direction.UP:
                direction = Direction.LEFT;
                break;
            case Direction.DOWN:
                direction = Direction.RIGHT;
                break;
            case Direction.LEFT:
                direction = Direction.DOWN;
                break;
            case Direction.RIGHT:
                direction = Direction.UP;
        }
        return direction;
    }

    /**
     * Used to take a direction, and give back a 90 degrees clockwise rotation.
     * @param direction An integer that is either: 0,1,2,3. Those numbers correspond to up, down, left, right, respectively.
     * @return The 90 degree clockwise rotation of a given direction.
     */
    public static int clockwise90(int direction){
        //takes in a direction and rotates it clockwise 90 degrees
        switch (direction){
            case Direction.UP:
                direction = Direction.RIGHT;
                break;
            case Direction.DOWN:
                direction = Direction.LEFT;
                break;
            case Direction.LEFT:
                direction = Direction.UP;
                break;
            case Direction.RIGHT:
                direction = Direction.DOWN;
        }
        return direction;
    }
}
