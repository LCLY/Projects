/**
 * @author asma
 */
public class Square extends Shape {
    private double length;

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        return this.length * this.length;
    }

    @Override
    public double getPerimeter() {
        return this.length * 4;
    }

}
