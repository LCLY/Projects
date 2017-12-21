/**
 * Created by LeyYen on 2/29/2016.
 */

import java.util.UUID;

public class MP3 implements Sellable, Downloadable {
    String productName;

    MP3(String productName) { // initializes the productName instance variable
        this.productName = productName;
    }

    @Override
    public String getProductName() { // returns the name identifier of this MP3
        return this.productName;
    }

    @Override
    public double getPrice() { // returns the price of this MP3
        return 0.99;
    }

    //The price of the MP3 will always be fixed at 0.99
    @Override
    public String generateDownloadCode() { //generates a random download code for this MP3
        return UUID.randomUUID().toString();
    }
}
