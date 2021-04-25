/**
 * Used for Visual actors (a group of actors that essentially never change state). All relevant logic fully within this class.
 */
public class Visual extends Actor {
    //the Visual class (subclass of Actor) is used for empty actors. Visual objects are rendered.
    //Has no extra methods or attributes aside from type stuff
    /**
     * String that corresponds to the Actor.type used for fence instances of this class.
     */
    public static final String TYPE_F = "Fence";
    /**
     * String that corresponds to the Actor.type used for golden tree instances of this class.
     */
    public static final String TYPE_G = "GoldenTree";
    /**
     * String that corresponds to the Actor.type used for pool instances of this class.
     */
    public static final String TYPE_P = "Pool";
    /**
     * String that corresponds to the Actor.type used for pad instances of this class.
     */
    public static final String TYPE_D = "Pad";

    /**
     * Constructor for Visual. Used when creating/initialising Visual objects from a world file.
     * @param TYPE The nature of the Visual object. Should be one of Visual.TYPE_F, Visual.TYPE_G, Visual.TYPE_P, Visual.TYPE_D.
     * @param imageName Name of the image which will be used for object, without the file extension e.g. "gold-tree".
     * @param x X coordinate where the Visual object is initially placed.
     * @param y Y coordinate where the Visual object is initially placed.
     */
    public Visual(String TYPE, String imageName, int x, int y) {
        //path is one of: "fence" "gold-tree" "pool" "pad"
        super("res/images/" + imageName + ".png", TYPE, x, y);
    }

    /**
     * Used to update a Visual's state. In other words, the actions a Visual takes within a tick, of which there are none.
     */
    @Override
    public void update(){};
    //no logic needed. so each tick, nothing happens

}
