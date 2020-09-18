import bagel.*;
import bagel.util.Point;

public class Tree {
    private final Image treeImage = new Image("res/images/tree.png");
    private Point point;

    //constructor
    public Tree(Point point) {
        this.point = point;
    }

    //getters and setters
    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }

    //draw image
    public void drawTree(){
        this.treeImage.drawFromTopLeft(this.point.x,this.point.y);
    }
}
