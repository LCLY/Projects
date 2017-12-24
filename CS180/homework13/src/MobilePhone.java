/* Homework 13
        * Subclass
        * @author Ley Yen Choo, lab sec 01
        * @version March, 24 2016
        */
public class MobilePhone {

    private int ram;
    private int batteryLife;

    public MobilePhone(int ram, int batteryLife) {
        this.ram = ram;
        this.batteryLife = batteryLife;
    }

    public int getRAM() {
        return this.ram;
    }

    public int getBatteryLife() {
        return this.batteryLife;
    }

    public void upgradePhone(int extraRAM, int extraBatteryHours) {
        this.ram = this.ram + extraRAM;
        this.batteryLife = this.batteryLife + extraBatteryHours;
        // increment the ram using extraRAM
        // increment the batteryHours using extraBatteryHours
    }

    public static void main(String[] args) {
        MyPhone myPhone = new MyPhone(2, 5, "4S", "5.0");
        System.out.println(myPhone.getRAM()); //prints 2
        System.out.println(myPhone.getBatteryLife()); //prints 5
        System.out.println(myPhone.getMyPhoneVersion()); //prints 4S
        System.out.println(myPhone.getMyOSVersion()); //prints 5.0
        myPhone.upgradeMyPhone(1, 3, "6S", "9.0");
        System.out.println(myPhone.getRAM()); //prints 3
        System.out.println(myPhone.getBatteryLife()); //prints 8
        System.out.println(myPhone.getMyPhoneVersion()); //prints 6S
        System.out.println(myPhone.getMyOSVersion()); //prints 9.0
    }

}