
public class Store {

    // You can add instance variables as needed

    private String name;

    private double revenue;

    private double materialCosts;

    private DeliveryDriver[] drivers;

    private String storeName = " ";

    private int numDrivers;

    private int counter;

    private double profit;

    private double costOfLabor;

    private double expense = 50;

    private double income;

    public Store(String storeName, DeliveryDriver[] drivers) {
        this.storeName = storeName;
        this.drivers = drivers;
    }

    public Store(String storeName, int numDrivers) {
        this.drivers = new DeliveryDriver[numDrivers];
        for (int j = 0; j < numDrivers; j++) {
            this.drivers[j] = new DeliveryDriver("Driver" + j);
        }
        this.storeName = storeName;
        this.numDrivers = numDrivers;
    }

    String getStoreName() {

        return this.storeName;
    }

    DeliveryDriver[] getDrivers() {

        return this.drivers;
    }

    /**
     * Updates the store's financial information. This function
     * handles assigning orders to drivers if it's a delivery and
     * manages sending drivers out on delivery.
     *
     * @param item - purchased item being ordered
     */
    public void placeOrder(PurchasedItem item) {
        if (this.numDrivers == 1 && this.drivers.length == 1) {
            if (this.drivers[0].getNumOrders() < this.drivers[0].getMaxCapacity()) {
                this.drivers[0].pickupOrder(item);
                this.revenue += item.getSalePrice();
                this.materialCosts += item.getMaterialCost();
            } else if (this.drivers[0].getNumOrders() == this.drivers[0].getMaxCapacity()) {
                this.drivers[0].deliverOrders();
                this.drivers[0].pickupOrder(item);
                this.revenue += item.getSalePrice();
                this.materialCosts += item.getMaterialCost();
            }
        }

        if (this.numDrivers == 0 && this.drivers.length == 1) {
            if (this.drivers[0].getNumOrders() < this.drivers[0].getMaxCapacity()) {
                this.drivers[0].pickupOrder(item);
                this.revenue += item.getSalePrice();
                this.materialCosts += item.getMaterialCost();
            } else if (this.drivers[0].getNumOrders() == this.drivers[0].getMaxCapacity()) {
                this.drivers[0].deliverOrders();
                this.drivers[0].pickupOrder(item);
                this.revenue += item.getSalePrice();
                this.materialCosts += item.getMaterialCost();
            }
        }

        if (this.drivers.length > 1 && this.numDrivers == 0) {
            if (this.counter == this.drivers.length - 1 && this.drivers[counter].getNumOrders() ==
                    this.drivers[counter].getMaxCapacity()) {
                this.drivers[counter].deliverOrders();
                counter = 0;
                placeOrder(item);
            } else if (this.counter < this.drivers.length - 1 && this.drivers[counter].getNumOrders() ==
                    this.drivers[counter].getMaxCapacity()) {
                this.drivers[counter].deliverOrders();
                counter++;
                placeOrder(item);
            } else if (this.drivers[counter].getNumOrders() < this.drivers[counter].getMaxCapacity()) {
                this.drivers[counter].pickupOrder(item);
                this.revenue += item.getSalePrice();
                this.materialCosts += item.getMaterialCost();
            }
        }

        if (this.numDrivers > 1 && this.drivers.length > 1) {
            if (this.counter == this.numDrivers - 1 && counter == this.drivers.length - 1 &&
                    this.drivers[counter].getNumOrders() == this.drivers[counter].getMaxCapacity()) {
                this.drivers[counter].deliverOrders();
                counter = 0;
                placeOrder(item);
            } else if (counter < this.numDrivers - 1 && counter < this.drivers.length - 1 &&
                    this.drivers[counter].getNumOrders() == this.drivers[counter].getMaxCapacity()) {
                this.drivers[counter].deliverOrders();
                counter++;
                placeOrder(item);
            } else if (this.drivers[counter].getNumOrders() < this.drivers[counter].getMaxCapacity()) {
                this.drivers[counter].pickupOrder(item);
                this.revenue = revenue + item.getSalePrice();
                this.materialCosts += item.getMaterialCost();
            }
        }

    }


    /**
     * Cancels an order with the store. It works under the assumption
     * that this order has already been placed. Also, this function
     * won't reduce the store's total material cost, as the item is
     * already made and wasted.
     * <p>
     * <p>
     * This method will only fail to cancel an order if the item is
     * marked for delivery but the currently selected delivery driver
     * isn't holding the item / can't remove the item (it has likely
     * already been removed).
     *
     * @param item - the order to cancel
     * @return true if the order could be canceled, false otherwise
     */
    public boolean cancelOrder(PurchasedItem item) {
        int x = 0;

        for (int i = 0; i < this.drivers[this.counter].getNumOrders(); i++) {
            if (!item.equals(this.drivers[this.counter].getOrders()[i]) && item.isDelivery()) {
                x = 0;
            } else {
                this.revenue -= item.getSalePrice();
                this.drivers[this.counter].removeOrder(item);
                x = 1;
            }
        }
        if (x == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter method for a store's revenue.
     *
     * @return gross revenue
     */
    public double getGrossRevenue() {

        return this.revenue;
    }

    /**
     * Getter method for a store's material costs.
     *
     * @return material costs
     */
    public double getMaterialCosts() {

        return this.materialCosts;
    }

    /**
     * Calculates the store's net profit using one of these equivalent
     * equations:
     * <p>
     * <p>
     * <i>profit = $(revenue) - $(period costs)</i>
     * <p>
     * <i>profit = $(revenue) - $(material costs) - $(labor costs)</i>
     *
     * @return the net operating profit of the store at this point in
     * time
     */
    public double getNetProfit() {
        for (int i = 0; i < this.drivers.length; i++) {
            costOfLabor = costOfLabor + this.drivers[i].getMoneyEarned();
        }
        this.profit = this.revenue - this.materialCosts - this.costOfLabor;

        return this.profit;
    }

    /**
     * Calculates the store's net income. The traditional formula
     * used to calculate net income is:
     * <p>
     * <p>
     * <i>income = $(profit) - $(indirect costs)</i>
     *
     * @return net income
     */
    public double getNetIncome() {

        income = getNetProfit() * 0.85 - expense;

        return income;
    }


    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append(String.format("\nStore Info\n----------\nName: \"%s\"\n", this.name));
        ret.append(String.format("Revenue: $%.2f\nCosts: $%.2f\n", this.revenue, this.materialCosts));
        ret.append(String.format("Profit: $%.2f\nIncome: $%.2f\n", this.getNetProfit(), this.getNetIncome()));

        ret.append(String.format("\nDriver Info\n-----------\n"));
        int i = 1;
        for (DeliveryDriver driver : this.drivers)
            ret.append(String.format("%d.) %s\n", i++, driver.toString()));

        return ret.toString();
    }

    private void printStatistics(double expRevenue, double expProfit, double expIncome) {
        double revenue = this.getGrossRevenue();
        System.out.printf("Revenue: $%.2f\t\tExpected: $%.2f\t\t%% Diff: %f%%\n",
                revenue, expRevenue, percentDiff(expRevenue, revenue));

        double profit = this.getNetProfit();
        System.out.printf("Profit: $%.2f\t\tExpected: $%.2f\t\t%% Diff: %f%%\n",
                profit, expProfit, percentDiff(expProfit, profit));

        double income = this.getNetIncome();
        System.out.printf("Income: $%.2f\t\tExpected: $%.2f\t%% Diff: %f%%\n",
                income, expIncome, percentDiff(expIncome, income));
    }

    private static double percentDiff(double from, double to) {
        return Math.abs((to - from) / from * 100.0);
    }

}
