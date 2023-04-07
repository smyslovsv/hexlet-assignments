package exercise;

// BEGIN
public class Circle {
    private Point point;
    private final int radius;

    Circle(Point point, int radius)  {
        this.point = point;
        this.radius = radius;
    }
    public int getRadius() {
        return radius;
    }
    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("");
        }
        return Math.PI * radius * radius;
    }
}
// END
