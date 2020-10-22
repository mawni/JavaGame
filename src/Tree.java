/*
project 1 sample solution used as base
*/

import bagel.Font;

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

    @Override
    public void render(){
        super.render(); //this uses the default implementation of superclass render to draw the actual tree
        Font font = new Font(ShadowLife.FONT_FILE, ShadowLife.FONT_SIZE);
        font.drawString(String.valueOf(fruit), getX(), getY());
    }
}
