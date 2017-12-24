/**
 * Created by LeyYen on 3/5/2016.
 */
public class SideOrder implements PurchasedItem {
    private String name;
    private double matCost;
    private double sellprice;
    private OrderSize size;
    private int delTime;
    private static final double COST_SMALL = 0.0;
    private static final double COST_MEDIUM = 0.40;
    private static final double COST_LARGE = 0.80;
    private static final double COST_ABSURB = 1.50;
    private static final double PRICE_SMALL = 0.0;
    private static final double PRICE_MEDIUM = 2.00;
    private static final double PRICE_LARGE = 3.00;
    private static final double PRICE_ABSURB = 4.50;
    private boolean result = true;


    public SideOrder(String name, double matCost, double sellPrice) {
        this.name = name;
        this.matCost = matCost;
        this.sellprice = sellPrice;
        this.size = OrderSize.SMALL;
        this.delTime = 0;

    }

    public SideOrder(String name, double matCost, double sellPrice, int delTime) {
        this(name, matCost, sellPrice);
        this.delTime = delTime;
        this.size = OrderSize.SMALL;
    }

    public SideOrder(String name, double matCost, double sellPrice, int delTime, OrderSize size) {
        this(name, matCost, sellPrice, delTime);
        this.size = size;
    }

    public OrderSize getOrderSize() {
        return this.size;
    }

    public void setOrderSize(OrderSize size) {
        this.size = size;
    }

    @Override
    public boolean isDelivery() {
        if (delTime >= 0 && delTime <= 30 && result) {
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
        if (this.size == OrderSize.SMALL) {
            return this.matCost + COST_SMALL;
        } else if (this.size == OrderSize.MEDIUM) {
            return this.matCost + COST_MEDIUM;
        } else if (this.size == OrderSize.LARGE) {
            return this.matCost + COST_LARGE;
        } else if (this.size == OrderSize.ABSURD) {
            return this.matCost + COST_ABSURB;
        } else {
            return 0;
        }
    }

    @Override
    public double getSalePrice() {
        if (this.size == OrderSize.SMALL) {
            return this.sellprice + PRICE_SMALL;
        } else if (this.size == OrderSize.MEDIUM) {
            return this.sellprice + PRICE_MEDIUM;
        } else if (this.size == OrderSize.LARGE) {
            return this.sellprice + PRICE_LARGE;
        } else if (this.size == OrderSize.ABSURD) {
            return this.sellprice + PRICE_ABSURB;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof SideOrder) {
            SideOrder other = (SideOrder) obj;
            return (other.getCustomerName().equals(getCustomerName()) && other.getDeliveryTime() == getDeliveryTime() &&
                    other.getMaterialCost() == getMaterialCost() && other.getSalePrice() == getSalePrice() &&
                    other.getOrderSize() == getOrderSize());

        } else {
            return false;

        }
    }
}
