import bagel.Image;

import java.util.ArrayList;

/*
project 1 sample solution used as base
*/

public abstract class Actor {
    private int x;
    private int y;
    private int prevX, prevY;
    private static ArrayList<Actor> arrActors;
        //array list in Actor-class is always more up to date than that in ShadowLife class
        //this is because Actor subclasses will be changing it superclass static arrActors within their update() methods
        //this superclass array list exists because subclasses themselves need to be aware of other actors in the game

    private final Image image;
    public final String type;

    public int getX() {return x;}
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {return y;}
    public void setY(int y) {
        this.y = y;
    }

    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
        this.prevX = x;
        this.prevY = y;
    }

    public static void setArrActors(ArrayList<Actor> actors){
        arrActors = actors;
    }
    public static ArrayList<Actor> getArrActors(){
        return arrActors;
    }
    /*public static void addActorToFront(Actor actor){
        //this will be used for adding gatherers primarily so that they're always before thieves
        arrActors.add(0, actor);
    }*/
    public static void addActorToEnd(Actor actor){
        //this will be used for adding thieves primarily, so that they're always after gatherers
        arrActors.add(actor);
    }
    public static Actor getLastFromList(){
        return arrActors.get(arrActors.size()-1);
    }
    public static void makeActorEmpty(Object actor){
        //take in what should be an existing item in the array list, find its index, then make that item null
        arrActors.set(arrActors.indexOf(actor), null);
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

    public void move(int direction){
        //move based on a given direction
        switch (direction) {
            case Direction.UP:
                //Direction.UP = a constant. Value = 0
                move(0, -ShadowLife.TILE_SIZE);
                break;
            case Direction.DOWN:
                move(0, ShadowLife.TILE_SIZE);
                break;
            case Direction.LEFT:
                move(-ShadowLife.TILE_SIZE, 0);
                break;
            case Direction.RIGHT:
                move(ShadowLife.TILE_SIZE, 0);
                break;
        }
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
            if(actor != null){
                if (actor.type.equals(type)){
                    if (this.x == actor.x && this.y == actor.y){
                        return true;
                    }
                }
            }
        }
        return false;
        //false if true was not returned in loop
    }


    //how this method works: RETURNS THE REFERENCE TO AN ACTOR.
    //reference returned is the same reference used for actual actor object in question. means that the object itself...
    //...can have it's attributes modified through this reference. BUT the reference is passed-by-value, so if the ...
    //...returned reference is changed, the object's original reference is unaffected
    public Actor atActorGetObject(String targetType){
        //pass in an actor type to see if #this# actor is on it, and give the reference to found actor (if found)
        for (Actor actor : arrActors){
            if (actor != null){
                if (actor.type.equals(targetType)){
                    if (this.x == actor.x && this.y == actor.y){
                        return actor;
                    }
                }
            }
        }
        return null;
        //null if objects not at same place
    }

    public void setAttribute(int val){
        //empty but will be overridden in relevant subclasses to modify an attribute e.g. Tree class to modify fruit int
    }
    public int getAttribute(){
        //empty but will be overridden in relevant subclasses to get an int attribute e.g. Tree class to get fruit int
        return -1;
        //note this superclass method could be used by overridden subclass method with super.getAttribute()
        //but in this case, that would be useless
    }

    public static void moveThievesToEnd(){
        //exactly as described, moves all thieves to end
        //this is needed because mitosis pool leads to new gatherers put after pre-existing thieves
        ArrayList<Actor> tempThief = new ArrayList<Actor>();
        for (int i = 0; i<arrActors.size(); i++){
            if (arrActors.get(i) == null){
                arrActors.remove(i);
            } else if (arrActors.get(i).type.contains(Thief.TYPE)){
                //if it's a thief
                tempThief.add(arrActors.get(i));
                arrActors.remove(i);
            }
        }
        arrActors.addAll(tempThief);
    }
}
