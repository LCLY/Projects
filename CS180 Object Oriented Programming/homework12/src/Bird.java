/**
 * Created by LeyYen on 2/26/2016.
 */
public class Bird implements Animal {

    @Override
    public int numberOfLegs() {
        return 2;
    }

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public String makeNoise() {
        return "Caw!";
    }
}