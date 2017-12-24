/**
 * Created by LeyYen on 2/26/2016.
 */
public class Mosquito implements Animal {

    @Override
    public int numberOfLegs() {
        return 6;
    }

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public String makeNoise() {
        return "Buzz!";
    }
}
