
/*
project 1 sample solution used as base
*/

import java.util.ArrayList;

public class Gatherer extends Actor {
    public static final String TYPE = "Gatherer";
    private int direction;
    private boolean carrying;
    private boolean active;

    //private int tickCounter = -1;
    //private static final int RESET_FREQUENCY = 5;

    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        direction = Direction.LEFT;
        carrying = false;
        active = true;
    }

    @Override
    public void update() {
        //update runs each tick.

        /*if (++tickCounter == RESET_FREQUENCY) {
            //if currently tick 5, change direction then reset tickCounter to 0
            direction = Direction.getRandom();
            tickCounter = 0;
        }*/

        if (active == true){
            //each tick, if active, move 1 tile based on current direction
            switch (direction) {
                case Direction.UP:
                    //Direction.UP = a constant. Value = 0
                    move(0, -ShadowLife.TILE_SIZE);
                    break;
                case Direction.DOWN:
                    move(0, ShadowLife.TILE_SIZE);
                    break;
                case Direction.LEFT:
                    move(-ShadowLife.TILE_SIZE, 0);
                    break;
                case Direction.RIGHT:
                    move(ShadowLife.TILE_SIZE, 0);
                    break;
            }
        }

        if (atActor(Visual.TYPE_F)){
            //if at any fences
            active = false;
            //move to previous position
            prevPosition();
        }

        //LINES 6-10
        if (atActor(Visual.TYPE_P)){
            //if at pool then do a bunch of stuff
        }

        if (atActor(Sign.TYPE)){
            //if at a sign

        }

    }
}
