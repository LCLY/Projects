/**
 * Created by LeyYen on 3/5/2016.
 */
public class Sandwich implements PurchasedItem {
    private String name;
    private double matCost;
    private double sellPrice;
    private int delTime;
    private Spicyness level;
    private int condiments;
    public static double costOfCondiment = 0.05;
    public static double pricePerCondiment = 0.75;
    private boolean result = true;

    public Sandwich(String name, double matCost) {
        this.name = name;
        this.matCost = matCost;
        this.condiments = 0;
        this.level = Spicyness.MILD;
        this.delTime = 0;
        this.sellPrice = this.matCost * 350 / 100;
    }

    public Sandwich(String name, double matCost, double sellPrice) {
        this(name, matCost);
        this.sellPrice = sellPrice;
        this.condiments = 0;
        this.level = Spicyness.MILD;
        this.delTime = 0;
    }

    public Sandwich(String name, double matCost, double sellPrice, int delTime, Spicyness level, int condiments) {
        this(name, matCost, sellPrice);
        this.delTime = delTime;
        this.level = level;
        this.condiments = condiments;
    }


    public Spicyness getSpicyness() {
        return this.level;
    }

    public void setSpicyness(Spicyness level) {
        this.level = level;
    }

    public void addCondiments(int num) { // Adds the specified number of condiments to the sandwich.
        this.condiments += num;
    }

    public void removeCondiments(int num) {
        if (num > this.condiments) {
            this.condiments = 0;
        } else {
            this.condiments = this.condiments - num;
        }

    } /*Removes the specified number of condiments from the sandwich. If more
     condiments are requested to be removed than there are condiments
     on the sandwich, all the condiments should be removed.*/


    public int getNumCondiments() { // Returns the number of condiments in a sandwich.
        return this.condiments;
    }

    @Override
    public boolean isDelivery() {
        if (this.delTime >= 0 && this.delTime <= 60 && result) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getCustomerName() {
        return this.name;
    }

    @Override
    public int getDeliveryTime() {
        return this.delTime;
    }

    @Override
    public void setDeliveryTime(int time) {

        if (time <= 0) {
            time = 0;
            result = false;
        }
        this.delTime = time;
    }

    @Override
    public double getMaterialCost() {
        return this.matCost + this.condiments * costOfCondiment;
    }

    @Override
    public double getSalePrice() {
        return this.sellPrice + this.condiments * pricePerCondiment;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Sandwich) {
            Sandwich other = (Sandwich) obj;
            return (other.getCustomerName().equals(getCustomerName()) && other.getDeliveryTime() == getDeliveryTime() &&
                    other.getMaterialCost() == getMaterialCost() && other.getSalePrice() == getSalePrice() &&
                    other.getSpicyness() == getSpicyness());

        } else {
            return false;
        }
    }


}
