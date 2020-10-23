import bagel.Font;

/**
 * Used for HoardStock actors (Hoards and Stockpiles). All relevant logic fully within this class
 */
public class HoardStock extends Actor {
    /**
     * String that corresponds to the Actor.type used for hoard instances of this class.
     */
    public static final String TYPE_H = "Hoard";
    /**
     * String that corresponds to the Actor.type used for stockpile instances of this class.
     */
    public static final String TYPE_S = "Stockpile";
    private int fruit;

    /**
     * Constructor for HoardStock. Used when creating/initialising hoards or stockpiles from a world file.
     * @param TYPE The nature of the HoardStock object. Should be either HoardStock.TYPE_H or HoardStock.TYPE_S.
     * @param imageName Name of the image which will be used for object, without the file extension e.g. "hoard".
     * @param x X coordinate where the HoardStock is initially placed.
     * @param y Y coordinate where the HoardStock is initially placed.
     */
    public HoardStock(String TYPE, String imageName, int x, int y) {
        super("res/images/" + imageName + ".png", TYPE, x, y);
        fruit = 0;
    }

    /**
     * Used to get the current amount of fruit that a HoardStock holds.
     * @return Number of fruit held by HoardStock object.
     */
    private int getFruit() {
        return fruit;
    }

    /**
     * Used to set/change the number of fruit that a tree holds.
     * @param fruit The number to change HoardStock.fruit to.
     */
    private void setFruit(int fruit) {
        this.fruit = fruit;
    }

    /**
     * Used to update a HoardStock's state. In other words, the actions a hoard or stockpile takes within a tick, of which there are none.
     */
    @Override
    public void update() {}
    //implemented with zero logic

    /**
     * Used to set/change the number of fruit that a hoard or stockpile holds (by calling setFruit(...)).
     * @param fruit The number to change HoardStock.fruit to.
     */
    @Override
    protected void setAttribute(int fruit){
        setFruit(fruit);
    }

    /**
     * Used to get the number of fruit that a hoard or stockpile holds (by calling getFruit()).
     * @return Number of fruit held by HoardStock object.
     */
    @Override
    protected int getAttribute(){
        return getFruit();
    }

    /**
     * Renders a HoardStock object onto the window based on its coordinates, and also renders a font showing current HoardStock.fruit.
     */
    @Override
    public void render(){
        super.render(); //this uses the default implementation of superclass render to draw the actual hoard or stockpile
        Font font = new Font(ShadowLife.FONT_FILE, ShadowLife.FONT_SIZE);
        font.drawString(String.valueOf(fruit), getX(), getY());
    }
}