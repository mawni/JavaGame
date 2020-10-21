public class Visual extends Actor {
    //the Visual class (subclass of Actor) is used for empty actors. Visual objects are rendered.
    //Has no extra methods or attributes aside from type stuff

    public static final String TYPE_F = "Fence";
    public static final String TYPE_G = "GoldenTree";
    public static final String TYPE_P = "Pool";
    public static final String TYPE_D = "Pad";

    public Visual(String TYPE, String path, int x, int y) {
        //path is one of: "fence" "gold-tree" "pool" "pad"
        super("res/images/" + path + ".png", TYPE, x, y);
    }

    @Override
    public void update(){};
    //no logic needed. so each tick, nothing happens

}
