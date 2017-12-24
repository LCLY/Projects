/**
 * Created by LeyYen on 2/29/2016.
 */

import java.util.UUID;

public class MP4 implements Sellable, Downloadable { //This class models an MP4 in the MusicStore.
    private String productName;
    private VideoType type;
    private static final double VIDEO_PRICE = 4.99;
    private static final double VIDEO_TVSERIES = 19.99;
    MP4(String productName, VideoType type) { // initializes the corresponding instance variables
        this.productName = productName;
        this.type = type;
    }

    @Override
    public String getProductName() { // returns the name identifier of this MP4
        return this.productName;
    }

    @Override
    public double getPrice() { //returns the price of this MP4
        if (this.type == VideoType.MOVIE) {
            return VIDEO_PRICE;
        } else {
            return VIDEO_TVSERIES;
        }
    }
    //The price of the MP4 will always be 4.99 if its type is MOVIE or 19.99 if its type is TVSERIES

    @Override
    public String generateDownloadCode() {//generates a random download code for this MP4
        return UUID.randomUUID().toString();
    }

    public VideoType getType() { //gets the type of this DVD
        return this.type;
    }


}
