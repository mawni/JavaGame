public class Thief extends Actor {
    public static final String TYPE = "Thief";
    private int direction;
    private int tickCounter = -1;
    private static final int RESET_FREQUENCY = 5;

    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);
        direction = Direction.getRandom();
    }

    @Override
    public void update() {
        //update runs each tick.
        if (++tickCounter == RESET_FREQUENCY) {
            //if currently tick 5, change direction then reset tickCounter to 0

            direction = Direction.getRandom();
            //TEMPORARY!!!!!!!!!!!!!!!!! Need to properly implement direction logic

            tickCounter = 0;
        }
        System.out.println(tickCounter);
        //each tick, move based on current direction
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
}
