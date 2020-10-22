import bagel.Font;

public class HoardStock extends Actor {
    public static final String TYPE_H = "Hoard";
    public static final String TYPE_S = "Stockpile";
    private int fruit;

    public HoardStock(String TYPE, String path, int x, int y) {
        super("res/images/" + path + ".png", TYPE, x, y);
        fruit = 0;
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
        super.render(); //this uses the default implementation of superclass render to draw the actual hoard or stockpile
        Font font = new Font(ShadowLife.FONT_FILE, ShadowLife.FONT_SIZE);
        font.drawString(String.valueOf(fruit), getX(), getY());
    }
}