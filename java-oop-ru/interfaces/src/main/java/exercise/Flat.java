package exercise;

// BEGIN
class Flat implements Home {
    private double area;
    private int floor;
    private double balconyArea;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    public String toString() {
        return "Квартира площадью " + this.getArea() + " метров на " + this.floor + " этаже";
    }

    public double getArea() {
        return this.area + this.balconyArea;
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
