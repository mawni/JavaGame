import bagel.*;
import bagel.util.Point;

public class Gatherer {
    private final Image gathererImage = new Image("res/images/gatherer.png");
    private CustomPoint point;
    private int direction;

    //constructor
    public Gatherer(CustomPoint point) {
        this.point = point;
    }

    //getters and setters
    public CustomPoint getPoint() {
        return point;
    }
    public void setPoint(CustomPoint point) {
        this.point = point;
    }
    public int getDirection(){
        return direction;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }

    //draw image
    public void drawGath(){
        this.gathererImage.drawFromTopLeft(this.point.getX(),this.point.getY());
    }
}
