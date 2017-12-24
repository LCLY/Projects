/**
 * @author asma
 */
public abstract class Shape {
    int locX;
    int locY;

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

}
