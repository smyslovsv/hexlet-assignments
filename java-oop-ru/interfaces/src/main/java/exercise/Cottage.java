package exercise;

// BEGIN
class Cottage implements Home {
    private double area;
    private int floor;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }
    public String toString() {
        return floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }

    public double getArea() {
        return this.area;
    }

    public int compareTo(Home another) {
        if (this.getArea() > another.getArea()) {
            return 1;
        } else if (this.getArea() < another.getArea()){
            return -1;
        } else {
            return 0;
        }
    }
}
// END
