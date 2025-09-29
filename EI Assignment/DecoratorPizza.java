interface Pizza {
    String getDescription();
    double getCost();
}

class BasicPizza implements Pizza {
    public String getDescription() {
        return "Plain Pizza";
    }
    public double getCost() {
        return 5.0;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;
    PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class CheeseDecorator extends PizzaDecorator {
    CheeseDecorator(Pizza pizza) { super(pizza); }
    public String getDescription() { return pizza.getDescription() + ", Cheese"; }
    public double getCost() { return pizza.getCost() + 2.0; }
}

class OliveDecorator extends PizzaDecorator {
    OliveDecorator(Pizza pizza) { super(pizza); }
    public String getDescription() { return pizza.getDescription() + ", Olives"; }
    public double getCost() { return pizza.getCost() + 1.5; }
}

// Client
public class DecoratorPizza{
    public static void main(String[] args) {
        Pizza pizza = new BasicPizza();
        System.out.println(pizza.getDescription() + " $" + pizza.getCost());

        pizza = new CheeseDecorator(pizza);
        pizza = new OliveDecorator(pizza);

        System.out.println(pizza.getDescription() + " $" + pizza.getCost());
    }
}
