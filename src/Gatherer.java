
/*
project 1 sample solution used as base
*/

/**
 * Used for Gatherer actors. All relevant logic fully within this class
 */
public class Gatherer extends Actor {
    /**
     * String that corresponds to the Actor.type used for all instances of this class.
     */
    public static final String TYPE = "Gatherer";
    private int direction;
    private boolean carrying;
    private boolean active;

    /**
     * Constructor for the Gatherer. Used when creating/initialising gatherers from a world file.
     * @param x X coordinate where the gatherer is initially placed.
     * @param y Y coordinate where the gatherer is initially placed.
     */
    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        direction = Direction.LEFT;
        carrying = false;
        active = true;
    }

    /**
     * Constructor for the Gatherer. Used to create a new gatherer when an existing one steps on a mitosis pool.
     * @param x X coordinate where the gatherer is initially placed.
     * @param y Y coordinate where the gatherer is initially placed.
     * @param direction The direction the gatherer will initially take.
     */
    private Gatherer(int x, int y, int direction) {
        super("res/images/gatherer.png", TYPE, x, y);
        this.direction = direction;
        carrying = false;
        active = true;
    }

    /**
     * Used to update a gatherer's state. In other words, the actions a gatherer takes within a tick.
     */
    @Override
    public void update() {
        //update runs each tick.

        if (active == true){
            //each tick, if active, move 1 tile based on current direction
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

        if (atActor(Visual.TYPE_F)){
            //if at any fences
            active = false;
            //move to previous position
            prevPosition();
        }


        if (atActor(Visual.TYPE_P)){
            //if at mitosis pool
            addActorToEnd(new Gatherer(this.getX(), this.getY(), Direction.antiClockwise90(this.direction)));
            getLastFromList().move(Direction.antiClockwise90(this.direction));
            addActorToEnd(new Gatherer(this.getX(), this.getY(), Direction.clockwise90(this.direction)));;
            getLastFromList().move(Direction.clockwise90(this.direction));
            makeActorEmpty(this);
                //more or less delete current actor by making it null
            return; //end update(). no further action this tick
        }

        if (atActor(Sign.TYPE_U)){
            //if at a SignUp
            direction = Direction.UP;
        } else if (atActor(Sign.TYPE_D)){
            //if at SignDown
            direction = Direction.DOWN;
        } else if (atActor(Sign.TYPE_L)){
            //if at SignLeft
            direction = Direction.LEFT;
        } else if (atActor(Sign.TYPE_R)){
            direction = Direction.RIGHT;
        } //used a series of if and else if so that if one completes, it doesn't check for remaining signs


        if (carrying == false){
            Actor treeReference = atActorGetObject(Tree.TYPE);
            if (treeReference != null && (treeReference instanceof Tree)) {
                //if at tree (& double checking object is actually tree)
                //((Tree) treeReference).getFruit(); //can be done with casting or superclass override. I chose override
                if (treeReference.getAttribute()>=1){
                    //if at least 1 fruit
                    treeReference.setAttribute(treeReference.getAttribute()-1);
                    //decrease fruit by 1
                    tookFromTree();
                }
            }
            treeReference = atActorGetObject(Visual.TYPE_G); //checking for golden tree
            if (treeReference != null && (treeReference instanceof Visual)){
                //if at golden tree (& double checking object is of correct class)
                //gold tree has infinite fruit, no number changing needed
                tookFromTree();
            }
        }


        Actor hoardRef = atActorGetObject(HoardStock.TYPE_H); //hoard
        if (hoardRef != null && (hoardRef instanceof HoardStock)){
            //if at hoard
            hoardStockLogic(hoardRef);
        }
        Actor stockRef = atActorGetObject(HoardStock.TYPE_S); //stockpile
        if (stockRef != null && (stockRef instanceof HoardStock)){
            //if at stockpile
            hoardStockLogic(stockRef);
        }

    }

    //Note many methods within each Actor subclass are made private. This is because the actions that actor subclasses
    //take upon a tick are fully encapsulated within that subclass itself. That is a public user should have no need for
    //any of them.
    /**
     * Used to update the state of a gatherer when it takes fruit from a tree.
     */
    private void tookFromTree(){
        carrying = true;
        flip180();
    }

    /**
     * Used to update the state of a gatherer when it steps on a hoard or stockpile.
     * @param hoardStock Reference that points to a HoardStock object.
     */
    private void hoardStockLogic(Actor hoardStock){
        if (carrying){
            carrying = false;
            hoardStock.setAttribute(hoardStock.getAttribute()+1);
            //increase fruit by 1
        }
        flip180();
    }

    /**
     * Used to flip the direction of a gatherer by 180 degrees.
     */
    private void flip180(){
        switch (direction){
            //rotate direction 180deg
            case Direction.UP:
                direction = Direction.DOWN;
                break;
            case Direction.DOWN:
                direction = Direction.UP;
                break;
            case Direction.LEFT:
                direction = Direction.RIGHT;
                break;
            case Direction.RIGHT:
                direction = Direction.LEFT;
        }
    }

}
