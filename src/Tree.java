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

    public int getFruit() {
        return fruit;
    }
    public void setFruit(int fruit) {
        this.fruit = fruit;
    }

    @Override
    public void update() {}
    //implemented with zero logic

    @Override
    public void setAttribute(int fruit){
        setFruit(fruit);
    }
    @Override
    public int getAttribute(){
        return getFruit();
    }
}
