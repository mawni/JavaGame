//this is class is created because the existing Point class uses final variables. This one is not final
public class CustomPoint {
    private double x;
    private double y;

    public CustomPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //getters and setters
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
}
