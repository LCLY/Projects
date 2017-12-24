
public class DeliveryDriver {

    // You can add instance variables as needed

    private String name;

    private double wage;

    private int maxCarriableItems;

    private int numDeliveries;

    private int minutesDelivering = 0;

    private int numItems = 0;

    private PurchasedItem[] items;

    public DeliveryDriver(String name) {
        this.name = name;
        this.wage = 7.25;
        this.maxCarriableItems = 5;
        this.items = new PurchasedItem[maxCarriableItems];
    }

    public DeliveryDriver(String name, double wage) {
        this(name);
        this.wage = wage;
        this.maxCarriableItems = 5;
        this.items = new PurchasedItem[maxCarriableItems];
    }

    public DeliveryDriver(String name, double wage, int maxCarriableItems) {
        this(name, wage);
        this.maxCarriableItems = maxCarriableItems;
        this.items = new PurchasedItem[maxCarriableItems];
    }

    public String getName() {

        return this.name;
    }

    public double getWage() {

        return this.wage;
    }

    public int getTimeSpent() {

        return this.minutesDelivering;
    }

    /**
     * * Consults the number of orders that the driver has delivered
     *
     * @return number of orders delivered
     */
    public int getNumDelivered() {

        return this.numDeliveries;
    }

    public int getMaxCapacity() {

        return this.maxCarriableItems;
    }


    /**
     * Add the order to the list/array of items to deliver.
     *
     * @param item - order to add
     * @return true if the item is for delivery and if the driver can
     * hold more orders, return false otherwise
     */
    public boolean pickupOrder(PurchasedItem item) {

        if (item.isDelivery()) {
            if (getNumOrders() < this.maxCarriableItems) {
                this.items[getNumOrders()] = item;
                this.numItems++;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Returns the number of items in the delivery list
     *
     * @return num items
     */
    public int getNumOrders() {

        return this.numItems;
    }


    /**
     * Return an array of items to deliver.
     * the array has to be populated within the index 0 to numItems - 1
     * and of size numItems
     *
     * @return array of type PurchasedItem
     */
    public PurchasedItem[] getOrders() {
        PurchasedItem[] temp = new PurchasedItem[this.numItems];
        for (int i = 0; i < this.numItems; i++) {
            temp[i] = this.items[i];
        }
        return temp;
    }

    /**
     * Update how long the driver has been delivering and empty the
     * list of items to deliver.
     */
    public void deliverOrders() {

        for (int i = 0; i < this.maxCarriableItems; i++) {
            if (this.items[i] != null) {
                this.minutesDelivering += this.items[i].getDeliveryTime();
                this.items[i] = null;
                this.numItems--;
                this.numDeliveries++;
            }
        }
    }

    /**
     * Check if driver is scheduled to deliver an order and remove it
     * and update the driver's counters if the item is found.
     *
     * @param item - order to remove from deliveries
     * @return true if the driver is scheduled to deliver the item,
     * false otherwise
     */
    public boolean removeOrder(PurchasedItem item) {

        boolean result = false;

        for (int i = 0; i < this.maxCarriableItems; i++) {
            if (item.equals(this.items[i])) {
                this.items[i] = null;
                this.numItems--;
                result = true;
                break;
            }
        }
        if (result) {

            for (int i = 0; i < this.maxCarriableItems; i++) {
                if (i < this.maxCarriableItems - 1 && this.items[i] == null) {
                    PurchasedItem temp = this.items[i];
                    this.items[i] = this.items[i + 1];
                    this.items[i + 1] = temp;
                }
            }
        }
        return result;

    }


    /**
     * Calculates the amount of money earned by the driver
     *
     * @return amount of money earned by the driver
     */
    public double getMoneyEarned() {
        if (this.minutesDelivering <= 480) {
            return (this.minutesDelivering / 60.0) * this.wage;
        } else {
            return ((this.minutesDelivering - 480) / 60.0) * 1.5 * this.wage + (480 / 60.0 * this.wage);
        }

    }

    /**
     * Compares if the input object is equal to the instance
     * Two objects are equal if they are of the same type and
     * all instance variables are equal.
     *
     * @return true if they are, false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof DeliveryDriver) {
            DeliveryDriver temp = (DeliveryDriver) obj;
            return (temp.getName().equals(getName())
                    && temp.getWage() == getWage()
                    && temp.getTimeSpent() == getTimeSpent()
                    && temp.getNumDelivered() == getNumDelivered());

        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append("Name: ");
        ret.append(this.name);

        ret.append(" - Wage: $");
        ret.append(String.format("%.2f", this.wage));

        ret.append(" - Can Carry: ");
        ret.append(this.maxCarriableItems);

        ret.append(" items - Num Deliveries: ");
        ret.append(this.numDeliveries);

        ret.append(" - Minutes Worked: ");
        ret.append(this.minutesDelivering);
        ret.append(" min");

        ret.append(" - Currently Carrying: ");
        ret.append(this.numItems);
        ret.append(" items");

        return ret.toString();
    }

}
