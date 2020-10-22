import bagel.Image;

public class Sign extends Actor {
    public static final String TYPE = "Sign";
    public static final String TYPE_U = "SignUp";
    public static final String TYPE_D = "SignDown";
    public static final String TYPE_L = "SignLeft";
    public static final String TYPE_R = "SignRight";
    private final int direction;
    //Sign was given its own class due to there being 4 diff directions

    public int getDirection(){
        return direction;
    }

    public Sign(String TYPE, int x, int y){
        //inputted type should be one of: "SignUp" "SignDown" "SignLeft" "SignRight"
        super("res/images/" + TYPE.replace("Sign","").toLowerCase() + ".png", TYPE, x, y);
            //the string manipulation should make "SignLeft" into "left" for example
        int temp = 0;
            //gave it a val to remove a warning
        switch (TYPE) {
            //note the numbering is consistent with the Direction class
            case TYPE_U: //SignUp
                temp = Direction.UP;
                break;
            case TYPE_D: //SignDown
                temp = Direction.DOWN;
                break;
            case TYPE_L: //SignLeft
                temp = Direction.LEFT;
                break;
            case TYPE_R: //SignRight
                temp = Direction.RIGHT;
        }
        this.direction = temp;
            //used a temp variable to eliminate an innocuous warning
    }



    @Override
    public void update(){};
}
