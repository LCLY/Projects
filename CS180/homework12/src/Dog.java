/**
 * Created by LeyYen on 2/26/2016.
 */
public class Dog implements Animal {

    @Override
    public int numberOfLegs() {
        return 4;
    }

    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    public String makeNoise() {
        return "Bark!";
    }
}
