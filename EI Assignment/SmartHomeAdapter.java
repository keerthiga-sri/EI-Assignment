interface SmartDevice {
    void turnOn();
    void turnOff();
}

class OldFan {
    public void start() {
        System.out.println("Old Fan is spinning...");
    }
    public void stop() {
        System.out.println("Old Fan stopped.");
    }
}

class FanAdapter implements SmartDevice {
    private OldFan fan = new OldFan();

    public void turnOn() {
        fan.start();
    }
    public void turnOff() {
        fan.stop();
    }
}

// Client
public class SmartHomeAdapter {
    public static void main(String[] args) {
        SmartDevice fan = new FanAdapter();

        fan.turnOn();
        fan.turnOff();
    }
}
