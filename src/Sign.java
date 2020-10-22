import bagel.Image;

public class Sign extends Actor {
    public static final String TYPE = "Sign";
    private final int direction;
    //Sign was given its own class due to there being 4 diff directions

    public Sign(String path, int x, int y){
        //path is one of: "up" "down" "left" "right"
        super("res/images/" + path + ".png", TYPE, x, y);
        int temp = 0;
            //gave it a val to remove a warning
        switch (path) {
            //note the numbering is consistent with the Direction class
            case "up":
                temp = Direction.UP;
                break;
            case "down":
                temp = Direction.DOWN;
                break;
            case "left":
                temp = Direction.LEFT;
                break;
            case "right":
                temp = Direction.RIGHT;
        }
        this.direction = temp;
    }



    @Override
    public void update(){};
}
