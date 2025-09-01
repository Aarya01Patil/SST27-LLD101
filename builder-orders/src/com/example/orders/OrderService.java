package com.example.orders;

import java.util.List;

public class OrderService {

    public Order createOrder(String id, String email, List<OrderLine> lines, Integer discount, boolean expedited, String notes) {
        return Order.builder(id, email)
                .addLines(lines)
                .discountPercent(discount)
                .expedited(expedited)
                .notes(notes)
                .build();
    }
}
