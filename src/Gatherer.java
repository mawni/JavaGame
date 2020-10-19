public class Gatherer extends Actor {
    public static final String TYPE = "Gatherer";
    private int direction;
    private int tickCounter = -1;
    private static final int RESET_FREQUENCY = 5;

    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);

        direction = Direction.getRandom();
    }

    @Override
    public void update() {
        if (++tickCounter == RESET_FREQUENCY) {
            direction = Direction.getRandom();
            tickCounter = 0;
        }

        switch (direction) {
            case Direction.UP:
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
