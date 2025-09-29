interface TravelStrategy {
    void travel(String from, String to);
}

class CarStrategy implements TravelStrategy {
    public void travel(String from, String to) {
        System.out.println("Traveling from " + from + " to " + to + " by Car (Fast but Costly).");
    }
}

class BusStrategy implements TravelStrategy {
    public void travel(String from, String to) {
        System.out.println("Traveling from " + from + " to " + to + " by Bus (Cheap but Slow).");
    }
}

class BikeStrategy implements TravelStrategy {
    public void travel(String from, String to) {
        System.out.println("Traveling from " + from + " to " + to + " by Bike (Eco-friendly).");
    }
}

class Navigator {
    private TravelStrategy strategy;

    public void setStrategy(TravelStrategy strategy) {
        this.strategy = strategy;
    }

    public void navigate(String from, String to) {
        strategy.travel(from, to);
    }
}

public class StrategyTravel {
    public static void main(String[] args) {
        Navigator nav = new Navigator();

        nav.setStrategy(new CarStrategy());
        nav.navigate("City A", "City B");

        nav.setStrategy(new BusStrategy());
        nav.navigate("City A", "City B");

        nav.setStrategy(new BikeStrategy());
        nav.navigate("City A", "City B");
    }
}
