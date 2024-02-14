import java.util.Comparator;
import java.util.List;

public class PaymentHandler {
    public Customer getNextPayer(List<Customer> customers) {
        return customers.stream()
                .min(Comparator.comparingDouble(Customer::getTotalPaid))
                .orElse(null);
    }
}
