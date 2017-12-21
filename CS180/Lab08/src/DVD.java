/**
 * Created by LeyYen on 2/29/2016.
 */
public class DVD implements Sellable {
    String productName;
    VideoType type;
    double price;

    DVD(String productName, VideoType type, double price) {// initializes the corresponding instance variables
        this.productName = productName;
        this.type = type;
        this.price = price;
    }

    @Override
    public String getProductName() {// returns the name identifier of this DVD
        return this.productName;
    }

    @Override
    public double getPrice() {// returns the price of this DVD
        return this.price;
    }

    public VideoType getType() { // gets the type of this DVD
        return this.type;
    }
}
