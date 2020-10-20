import bagel.Image;

/*
project 1 sample solution used as base
*/

public abstract class Actor {
    private int x;
    private int y;

    private final Image image;
    public final String type;

    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public final void tick() {
        update();
    }
    //all actors run tick() with each tick. Update() run within

    public void render() {
        image.drawFromTopLeft(x, y);
    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public abstract void update();
    //abstract, so all subclasses must implement update(), even if empty logic
}
