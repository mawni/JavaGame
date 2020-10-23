import bagel.Image;
import java.util.ArrayList;

/*
project 1 sample solution used as base
*/

/**
 * Used for any element of the game that appears on screen. Specifically elements loaded from a world file.
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

    /**
     * The type of the actor. A String description e.g. 'Gatherer'.
     */
    public final String type;

    /**
     * Used to get x attribute of actor.
     * @return The x attribute.
     */
    public int getX() {return x;}

    /**
     * Used to change the value of the x attribute.
     * @param x The value to change the object's x to.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Used to get y attribute of actor.
     * @return The object's y attribute.
     */
    public int getY() {return y;}

    /**
     * Used to change the value of the y attribute.
     * @param y The value to change the object's y to.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Constructor for Actor class. Used when filename, type, and x,y coordinates, are given.
     * @param filename Target path for the image of the actor.
     * @param type String description of the actor.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
        this.prevX = x;
        this.prevY = y;
    }

    /**
     * Sets Actor class' own static arrActors ArrayList<Actor> equal to an inputted one.
     * @param actors The ArrayList<Actor> used to set Actor-class arrActors.
     */
    public static void setArrActors(ArrayList<Actor> actors){
        arrActors = actors;
    }

    /**
     * Used to get the list of actors from Actor class.
     * @return The list of actors from Actor class, in same format as it exists.
     */
    public static ArrayList<Actor> getArrActors(){
        return arrActors;
    }
    /*public static void addActorToFront(Actor actor){
        arrActors.add(0, actor);
    }*/

    /**
     * Used to add an actor to the end of the static actor list in Actor-class.
     * @param actor The actor to be added to the list.
     */
    public static void addActorToEnd(Actor actor){
        arrActors.add(actor);
    }

    /**
     * Gets the last actor from Actor-class array list arrActors.
     * @return The last actor object in list.
     */
    public static Actor getLastFromList(){
        return arrActors.get(arrActors.size()-1);
    }

    /**
     * Makes an array list item equal to null. Used when an actor is removed from simulation.
     * @param actor The index of the array list actor object to make null.
     */
    public static void makeActorEmpty(Object actor){
        //take in what should be an existing item in the array list, find its index, then make that item null
        arrActors.set(arrActors.indexOf(actor), null);
    }

    /**
     * Used to make an Actor do whatever it is supposed to for a tick.
     */
    public final void tick() {
        update();
    }
    //all actors run tick() with each tick. Update() run within

    /**
     * Renders an actor onto the window based on that actor's coordinate attributes.
     */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /**
     * Moves an actor by adjusting (i.e. changing) its coordinates. Change is made according to inputted arguments.
     * Also sets the previous position (position before change was made).
     * @param deltaX Value to add to the existing x coordinate. Works as so: x=x+deltaX.
     * @param deltaY Value to add to the existing y coordinate. Works as so: y=y+deltaY.
     */
    public void move(int deltaX, int deltaY) {
        prevX = x;
        prevY = y;
        x += deltaX;
        y += deltaY;
    }

    /**
     * Moves an actor according to only a direction. Moves actor 1 tile in that direction.
     * @param direction Direction to move 1 tile. Up=0,Down=1,Left=2,Right=3.
     */
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

    /**
     * Moves an actor to its previous position, i.e. wherever it was last tick.
     */
    public void prevPosition(){
        //moves an actor to its previous coords (hence making the current coords the new prevX,Y)
        int tempX = x;
        int tempY = y;
        x = prevX;
        y = prevY;
        prevX = tempX;
        prevY = tempY;
    }

    /**
     * Updates an actor's state. Subclasses contain implementation. Superclass version (this one) does nothing.
     */
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
    /**
     * Used to find if a type of actor is at the same position as the current one. Then return the reference of that actor object.
     * Useful when wanted to alter contents of that found Actor object.
     * @param targetType The type of the actor to look for, e.g. "Gatherer".
     * @return The reference of the actor object found at same x,y position. = null if actor not found.
     */
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

    /**
     * Set the value of an actor's single defining (and changeable) int attribute. E.g. fruit int for Tree.
     * Subclasses will override/implement only if they even have such an attribute. Superclass base implementation is useless.
     * @param val The number to change an int attribute to.
     */
    public void setAttribute(int val){
        //empty but will be overridden in relevant subclasses to modify an attribute e.g. Tree class to modify fruit int
    }

    /**
     * Get the value of an actor's single defining (and changeable) int attribute. E.g. fruit int for Trees.
     * Subclasses will override/implement only if they even have such an attribute. Superclass base implementation is useless.
     * @return The value of the single defining attribute. Superclass base implementation returns -1.
     */
    public int getAttribute(){
        //empty but will be overridden in relevant subclasses to get an int attribute e.g. Tree class to get fruit int
        return -1;
        //note this superclass method could be used by overridden subclass method with super.getAttribute()
        //but in this case, that would be useless
    }

    /**
     * Used to move all Thief objects in Actor-class list arrActors to the end of said list.
     */
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
