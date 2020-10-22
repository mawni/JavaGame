public class HoardStock extends Actor {
    public static final String TYPE_H = "Hoard";
    public static final String TYPE_S = "Stockpile";
    private int fruit;

    public HoardStock(String TYPE, String path, int x, int y) {
        super("res/images/" + path + ".png", TYPE, x, y);
        fruit = 0;
    }

    public int getFruit(){
        return fruit;
    }

    @Override
    public void update() {}
    //implemented with zero logic
}