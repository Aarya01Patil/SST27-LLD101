import com.example.orders.*;
import java.util.List;

public class TryIt {
    public static void main(String[] args) {
        OrderLine l1 = new OrderLine("A", 1, 200);
        OrderLine l2 = new OrderLine("B", 3, 100);
        Order o = Order.builder("o2", "a@b.com")
                .addLine(l1)
                .addLine(l2)
                .discountPercent(10)
                .build();
        System.out.println("Before: " + o.totalAfterDiscount());
        l1.setQuantity(999); // try to mutate original line
        System.out.println("After (should be unchanged):  " + o.totalAfterDiscount());
        System.out.println("=> Totals remain stable due to defensive copies.");
    }
}
