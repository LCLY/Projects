/**
 * @author asma
 */
public class Circle extends Shape {
    private double radius;

    public void setRadius(double radius) {
        this.radius = radius;

    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);

    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;

    }

}
