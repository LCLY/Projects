
public class MyPhone extends MobilePhone {
    private String myPhoneVersion;
    private String myOSVersion;

    public MyPhone(int ram, int batteryLife, String myPhoneVersion, String myOSVersion) {
        super(ram, batteryLife);
        this.myPhoneVersion = myPhoneVersion;
        this.myOSVersion = myOSVersion;
    } //should call the parent constructor and then update the MyPhone specific fields

    public void upgradeMyPhone(int extraRAM, int extraBatteryLife, String newMyPhoneVersion, String newMyOSVersion) {
        super.upgradePhone(extraRAM, extraBatteryLife);
        this.myPhoneVersion = newMyPhoneVersion;
        this.myOSVersion = newMyOSVersion;
    } //calls the upgradePhone() method and then updates the myPhoneVersion and the myOSVersion

    public String getMyPhoneVersion() {
        return this.myPhoneVersion;
    } // returns the myPhoneVersion

    public String getMyOSVersion() {
        return this.myOSVersion;
    } // returns the myOSVersion
}
