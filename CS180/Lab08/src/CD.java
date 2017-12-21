/**
 * Created by LeyYen on 2/29/2016.
 */
public class CD implements Sellable {
    String productName;
    int totalSongs;
    double price;

    CD(String productName, int totalSongs, double price) {
        this.productName = productName;
        this.totalSongs = totalSongs;
        this.price = price;
    }

    // Implemented interfaces: Sellable
    @Override
    public String getProductName() {//returns the name identifier of this CD

        return this.productName;
    }

    @Override
    public double getPrice() { //returns the price of this CD
        return this.price;
    }

    public int getTotalSongs() {
        return this.totalSongs;
    }
}

