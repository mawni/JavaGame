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
                temp = 0;
                break;
            case "down":
                temp = 1;
                break;
            case "left":
                temp = 2;
                break;
            case "right":
                temp = 3;
        }
        this.direction = temp;
    }



    @Override
    public void update(){};
}
