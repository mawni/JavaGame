import bagel.Image;

/**
 * Used for Sign actors. All relevant logic fully within this class
 */
public class Sign extends Actor {
    /**
     * String that corresponds to nature of this class and all its instances as being a Sign.
     * The Actor.type of all instances is never just Sign.TYPE though.
     */
    public static final String TYPE = "Sign";
    /**
     * String that corresponds to the Actor.type used for SignUp instances of this class.
     */
    public static final String TYPE_U = "SignUp";
    /**
     * String that corresponds to the Actor.type used for SignDown instances of this class.
     */
    public static final String TYPE_D = "SignDown";
    /**
     * String that corresponds to the Actor.type used for SignLeft instances of this class.
     */
    public static final String TYPE_L = "SignLeft";
    /**
     * String that corresponds to the Actor.type used for SignRight instances of this class.
     */
    public static final String TYPE_R = "SignRight";

    //Sign was given its own class due to there being 4 diff directions

    /**
     * Constructor for Sign. Used when creating/initialising Sign objects from a world file.
     * @param TYPE The nature of the Sign object. Should be one of Sign.TYPE_U, Sign.TYPE_D, Sign.TYPE_L, Sign.TYPE_R.
     * @param x X coordinate where the Sign object is initially placed.
     * @param y Y coordinate where the Sign object is initially placed.
     */
    public Sign(String TYPE, int x, int y){
        //inputted type should be one of: "SignUp" "SignDown" "SignLeft" "SignRight"
        super("res/images/" + TYPE.replace("Sign","").toLowerCase() + ".png", TYPE, x, y);
            //the string manipulation should make "SignLeft" into "left" for example
    }

    /**
     * Used to update a Sign's state. In other words, the actions a sign takes within a tick, of which there are none.
     */
    @Override
    public void update(){};
}
