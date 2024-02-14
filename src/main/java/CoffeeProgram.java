import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class CoffeeProgram {
    public static void main(String[] args) {
        CustomerData data = new CustomerData();
        List<Customer> customers = data.loadCustomersFromFile("customers.json");

        if (customers == null || customers.isEmpty()) {
            System.out.println("Error loading customer data.");
            customers = initializeDefaultCustomers();
            data.saveCustomersToFile(customers, "customers.json");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to add a new customer? (Yes/No)");
        if (scanner.nextLine().equalsIgnoreCase("Yes")) {
            addNewCustomer(customers, scanner);
        }

        updateCustomers(customers, scanner);

        PaymentHandler paymentHandler = new PaymentHandler();
        Customer nextPayer = paymentHandler.getNextPayer(customers);

        if (nextPayer != null){
            System.out.println(nextPayer.getName() + " should pay for the coffee today.");
            double totalOrderPrice = calculateTotalOrderPrice(customers);
            nextPayer.setTotalPaid(nextPayer.getTotalPaid() + totalOrderPrice);
            data.saveCustomersToFile(customers, "customers.json");
        } else {
            System.out.println("Unable to determine who should pay for the coffee.");
        }

        scanner.close();
    }

    private static List<Customer> initializeDefaultCustomers() {
        List<Customer> defaultCustomers = new ArrayList<>();
        defaultCustomers.add(new Customer("Bob", "Cappuccino", 3.5, 0));
        defaultCustomers.add(new Customer("Jeremy", "Black Coffee", 2.5, 0));
        defaultCustomers.add(new Customer("Alice", "Latte", 4.0, 0));
        defaultCustomers.add(new Customer("Samantha", "Espresso", 2.0, 0));
        defaultCustomers.add(new Customer("Mike", "Americano", 3.0, 0));
        defaultCustomers.add(new Customer("Rachel", "Mocha", 4.5, 0));
        defaultCustomers.add(new Customer("Tom", "Iced Coffee", 3.5, 0));
        return defaultCustomers;
    }

    public static void addNewCustomer(List<Customer> customers, Scanner scanner) {
        System.out.println("Enter the name of the new customer:");
        String name = scanner.nextLine();

        System.out.println("What is their favorite coffee drink?");
        String coffeeType = scanner.nextLine();

        System.out.println("Enter the price of the coffee:");
        double coffeePrice = scanner.nextDouble();
        scanner.nextLine();

        Customer newCustomer = new Customer(name, coffeeType, coffeePrice, 0);
        customers.add(newCustomer);
        System.out.println("New customer added:" + name);
    }

    public static void updateCustomers(List<Customer> customers, Scanner scanner) {
        System.out.println("Did anyone not buy coffee today? (Yes/No)");
        if (scanner.nextLine().equalsIgnoreCase("Yes")){
            System.out.println("Enter the names of customers who did not buy coffee today (type 'done' when finished)");
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                if (name.equalsIgnoreCase("done")) break;
                customers.stream()
                        .filter(customer -> customer.getName().equalsIgnoreCase(name))
                        .findFirst()
                        .ifPresent(customer -> customer.setCoffeeType("None"));
            }
        }

        System.out.println("Did anyone change their drink choice today? (Yes/No)");
        if (scanner.nextLine().equalsIgnoreCase("Yes")){
            System.out.println("Enter the customer name followed by the drink they bought today (type 'done' when finished)");
            while (scanner.hasNextLine()) {
                System.out.println("Customer name:");
                String name = scanner.nextLine();
                if (name.equalsIgnoreCase("done")) break;

                System.out.println("What drink did they buy today?");
                String drink = scanner.nextLine();

                System.out.println("How much was their drink?");
                double price = scanner.nextDouble();
                scanner.nextLine();

                customers.stream()
                        .filter(customer -> customer.getName().equalsIgnoreCase(name))
                        .findFirst()
                        .ifPresent(customer -> {
                            customer.setCoffeeType(drink);
                            customer.setCoffeePrice(price);
                        });
            }
        }
    }
    private static double calculateTotalOrderPrice(List<Customer> customers) {
        return customers.stream()
                .filter(customer -> !customer.getCoffeeType().equals("None"))
                .mapToDouble(Customer::getCoffeePrice)
                .sum();
    }
}