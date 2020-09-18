import bagel.*;
import bagel.util.Point;

public class Tree {
    private final Image treeImage = new Image("res/images/tree.png");
    private CustomPoint point;

    //constructor
    public Tree(CustomPoint point) {
        this.point = point;
    }

    //getters and setters
    public CustomPoint getPoint() {
        return point;
    }
    public void setPoint(CustomPoint point) {
        this.point = point;
    }

    //draw image
    public void drawTree(){
        this.treeImage.drawFromTopLeft(this.point.getX(),this.point.getY());
    }
}
