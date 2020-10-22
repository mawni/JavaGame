public class Thief extends Actor {
    public static final String TYPE = "Thief";
    private int direction;
    private boolean carrying;
    private boolean active;
    private boolean consuming;

    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);
        direction = Direction.UP;
        carrying = false;
        consuming = false;
        active = true;
    }
    public Thief(int x, int y, int direction) {
        super("res/images/thief.png", TYPE, x, y);
        this.direction = direction;
        carrying = false;
        consuming = false;
        active = true;
    }

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
            //if at fence
            active = false;
            prevPosition(); //go to prev position
        }

        if (atActor(Visual.TYPE_P)){
            //if at mitosis pool
            addActorToEnd(new Thief(this.getX(), this.getY(), antiClockwise90(this.direction)));
            getLastFromList().move(antiClockwise90(this.direction));
            addActorToEnd(new Thief(this.getX(), this.getY(), clockwise90(this.direction)));;
            getLastFromList().move(clockwise90(this.direction));
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

        if (atActor(Visual.TYPE_D)){
            //if at a Pad
            consuming = true;
        }

        Actor gathererRef = atActorGetObject(Gatherer.TYPE);
        if (gathererRef != null && (gathererRef instanceof Gatherer)){
            //if thief is on a gatherer
            //note by nature of thief actors always after gatherer actors, any gatherer tick will already have been completed
            switch (direction){
                    //rotate direction 270deg clockwise
                    case Direction.UP:
                        direction = Direction.LEFT;
                        break;
                    case Direction.DOWN:
                        direction = Direction.RIGHT;
                        break;
                    case Direction.LEFT:
                        direction = Direction.DOWN;
                        break;
                    case Direction.RIGHT:
                        direction = Direction.UP;
            }
        }

        if (carrying == false){
            Actor treeReference = atActorGetObject(Tree.TYPE); //normal tree
            if (treeReference != null && (treeReference instanceof Tree)) {
                //if at tree (& double checking object is actually tree)
                if (treeReference.getAttribute()>=1){
                    //if at least 1 fruit
                    treeReference.setAttribute(treeReference.getAttribute()-1);
                    //decrease fruit by 1
                    carrying = true;
                }
            }
            treeReference = atActorGetObject(Visual.TYPE_G); //checking for golden tree
            if (treeReference != null && (treeReference instanceof Visual)){
                //if at golden tree (& double checking object is of correct class)
                //gold tree has infinite fruit, no number changing needed
                carrying = true;
            }
        }

        Actor hoardRef = atActorGetObject(HoardStock.TYPE_H); //hoard
        if (hoardRef != null && (hoardRef instanceof HoardStock)){
            //if at hoard
            if (consuming == true){
                consuming = false;
                if (carrying == false){
                    if (hoardRef.getAttribute()>=1){
                        //if hoard has at least one fruit
                        carrying = true;
                        hoardRef.setAttribute(hoardRef.getAttribute()-1);
                    } else {
                        clockwise90();
                    }
                }
            } else if (carrying == true) {
                carrying = false;
                hoardRef.setAttribute(hoardRef.getAttribute()+1);
                clockwise90();
            }
        }

        Actor stockRef = atActorGetObject(HoardStock.TYPE_S); //stockpile
        if (stockRef != null && (stockRef instanceof HoardStock)){
            //if at stockpile
            if (carrying==false){
                if (stockRef.getAttribute()>=1){
                    carrying = true;
                    consuming = false;
                    stockRef.setAttribute(stockRef.getAttribute()-1);
                    clockwise90();
                }
            } else{
                clockwise90();
            }
        }

    }

    public int antiClockwise90(int direction){
        //takes in a direction and rotates it counter clockwise 90 degrees
        switch (direction){
            case Direction.UP:
                direction = Direction.LEFT;
                break;
            case Direction.DOWN:
                direction = Direction.RIGHT;
                break;
            case Direction.LEFT:
                direction = Direction.DOWN;
                break;
            case Direction.RIGHT:
                direction = Direction.UP;
        }
        return direction;
    }

    public void clockwise90(){
        direction = clockwise90(direction);
        //this method just applies the change automatically
    }
    public int clockwise90(int direction){
        //takes in a direction and rotates it clockwise 90 degrees
        switch (direction){
            case Direction.UP:
                direction = Direction.RIGHT;
                break;
            case Direction.DOWN:
                direction = Direction.LEFT;
                break;
            case Direction.LEFT:
                direction = Direction.UP;
                break;
            case Direction.RIGHT:
                direction = Direction.DOWN;
        }
        return direction;
    }

}
