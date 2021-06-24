package npo.beato.spring_practice.device;

public class DeviceType {

    public final String MAC = "MacOSX";
    public final String WINDOW = "windows";
    public final String LINUX = "linux";
    public final String IOS = "ios";
    public final String ANDROID = "android";
    public final String WINDOW_PHONE = "windowsPhone";

    public String type(String device) {
        String type = "";

        switch (device) {
            case LINUX:
            case MAC:
            case WINDOW:
                type = "pc";
                break;
            case WINDOW_PHONE:
            case ANDROID:
            case IOS:
                type = "mob";
                break;
        }
        return type;
    }

    public boolean isMob(String device) {
        if(device.equals(ANDROID) || device.equals(IOS) || device.equals(WINDOW_PHONE) || device.equals(LINUX)) {
            return true;
        } else {
            return false;
        }
    }
}
