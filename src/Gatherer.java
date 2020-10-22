
/*
project 1 sample solution used as base
*/

public class Gatherer extends Actor {
    public static final String TYPE = "Gatherer";
    private int direction;
    private boolean carrying;
    private boolean active;

    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        direction = Direction.LEFT;
        carrying = false;
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
            //if at any fences
            active = false;
            //move to previous position
            prevPosition();
        }

        //LINES 6-10
        if (atActor(Visual.TYPE_P)){
            //if at mitosis pool
            //####################################
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

    public void tookFromTree(){
        carrying = true;
        flip180();
    }
    public void hoardStockLogic(Actor hoardStock){
        if (carrying){
            carrying = false;
            hoardStock.setAttribute(hoardStock.getAttribute()+1);
            //increase fruit by 1
        }
        flip180();
    }
    public void flip180(){
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
