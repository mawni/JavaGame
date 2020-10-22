import bagel.Image;

/*
project 1 sample solution used as base
*/

public abstract class Actor {
    private int x;
    private int y;
    private int prevX, prevY;
    private static Actor arrActors[];

    private final Image image;
    public final String type;

    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
        this.prevX = x;
        this.prevY = y;
    }

    public static void setArrActors(Actor[] actors){
        arrActors = actors;
    }

    public final void tick() {
        update();
    }
    //all actors run tick() with each tick. Update() run within

    public void render() {
        image.drawFromTopLeft(x, y);
    }

    public void move(int deltaX, int deltaY) {
        prevX = x;
        prevY = y;
        x += deltaX;
        y += deltaY;
    }

    public void prevPosition(){
        //moves an actor to its previous coords (hence making the current coords the new prevX,Y)
        int tempX = x;
        int tempY = y;
        x = prevX;
        y = prevY;
        prevX = tempX;
        prevY = tempY;
    }

    public abstract void update();
    //abstract, so all subclasses must implement update(), even if empty logic

    public boolean atActor(String type){
        //pass in an actor type to see if this actor is on it
        for (Actor actor : arrActors){
            if (actor.type.equals(type)){
                if (this.x == actor.x && this.y == actor.y){
                    return true;
                }
            }
        }
        return false;
        //false if true was not returned in loop
    }
}
