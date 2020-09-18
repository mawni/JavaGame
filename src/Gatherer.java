import bagel.*;
import bagel.util.Point;

public class Gatherer {
    private final Image gathererImage = new Image("res/images/gatherer.png");
    private Point point;

    //constructor
    public Gatherer(Point point) {
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
    public void drawGath(){
        this.gathererImage.drawFromTopLeft(this.point.x,this.point.y);
    }
}
