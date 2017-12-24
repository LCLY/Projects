/**
 * @author asma
 */
public class Rectangle extends Shape {
    private double width;
    private double length;

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        return this.width * this.length;
    }

    @Override
    public double getPerimeter() {
        return this.width * 2 + this.length * 2;
    }

}
