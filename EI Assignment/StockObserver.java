import java.util.*;

interface Investor {
    void update(String stock, double price);
}


class MobileApp implements Investor {
    public void update(String stock, double price) {
        System.out.println("Mobile App: " + stock + " updated to $" + price);
    }
}

class WebDashboard implements Investor {
    public void update(String stock, double price) {
        System.out.println("Web Dashboard: " + stock + " updated to $" + price);
    }
}

class StockMarket {
    private Map<String, Double> stocks = new HashMap<>();
    private List<Investor> investors = new ArrayList<>();

    public void addInvestor(Investor investor) {
        investors.add(investor);
    }

    public void setPrice(String stock, double price) {
        stocks.put(stock, price);
        notifyInvestors(stock, price);
    }

    private void notifyInvestors(String stock, double price) {
        for (Investor investor : investors) {
            investor.update(stock, price);
        }
    }
}

// Client
public class StockObserver {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        market.addInvestor(new MobileApp());
        market.addInvestor(new WebDashboard());

        market.setPrice("Tesla", 900.50);
        market.setPrice("Apple", 175.20);
    }
}
