/*
project 1 sample solution used as base
*/

public class Tree extends Actor {
    public static final String TYPE = "Tree";
    private int fruit;

    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);
        fruit = 3;
    }

    @Override
    public void update() {}
    //implemented with zero logic
}
