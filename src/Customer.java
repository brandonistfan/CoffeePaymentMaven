public class Customer {
    private String name;
    public String coffeeType;
    private double coffeePrice;
    private double totalPaid;

    public Customer() {
        name = " ";
        coffeeType = " ";
        coffeePrice = 0;
        totalPaid = 0;
    }
    public Customer(String custName, String custCoffeeType, double custCoffeePrice, double custTotalPaid) {
        setName(custName);
        setCoffeeType(custCoffeeType);
        setCoffeePrice(custCoffeePrice);
        setTotalPaid(custTotalPaid);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public double getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(double coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }
}
