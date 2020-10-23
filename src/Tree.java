/*
project 1 sample solution used as base
*/

import bagel.Font;

/**
 * Used for Tree actors. All relevant logic fully within this class
 */
public class Tree extends Actor {
    /**
     * String that corresponds to the Actor.type used for all instances of this class.
     */
    public static final String TYPE = "Tree";
    private int fruit;

    /**
     * Constructor for the Tree. Used when creating/initialising trees from a world file.
     * @param x X coordinate where the tree is initially placed.
     * @param y Y coordinate where the tree is initially placed.
     */
    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);
        fruit = 3;
    }

    /**
     * Used to get the current amount of fruit that a tree holds.
     * @return Number of fruit held by tree.
     */
    private int getFruit() {
        return fruit;
    }

    /**
     * Used to set/change the number of fruit that a tree holds.
     * @param fruit The number to change Tree.fruit to.
     */
    private void setFruit(int fruit) {
        this.fruit = fruit;
    }

    /**
     * Used to update a Tree's state. In other words, the actions a tree takes within a tick, of which there are none.
     */
    @Override
    public void update() {}
    //implemented with zero logic

    /**
     * Used to set/change the number of fruit that a tree holds (by calling setFruit(...)).
     * @param fruit The number to change Tree.fruit to.
     */
    @Override
    protected void setAttribute(int fruit){
        setFruit(fruit);
    }

    /**
     * Used to get the number of fruit that a tree holds (by calling getFruit()).
     * @return Number of fruit held by tree.
     */
    @Override
    protected int getAttribute(){
        return getFruit();
    }

    /**
     * Renders a tree onto the window based on its coordinates, and also renders a font showing current Tree.fruit.
     */
    @Override
    public void render(){
        super.render(); //this uses the default implementation of superclass render to draw the actual tree
        Font font = new Font(ShadowLife.FONT_FILE, ShadowLife.FONT_SIZE);
        font.drawString(String.valueOf(fruit), getX(), getY());
    }
}
