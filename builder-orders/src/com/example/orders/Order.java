package com.example.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Immutable Order aggregate with Builder and defensive copies for lines.
 */
public final class Order {
    private final String id;
    private final String customerEmail;
    private final List<OrderLine> linesSnapshot; // internal defensive copy
    private final Integer discountPercent; // 0..100
    private final boolean expedited;
    private final String notes;

    private Order(Builder b) {
        this.id = b.id;
        this.customerEmail = b.customerEmail;
        this.discountPercent = b.discountPercent;
        this.expedited = b.expedited;
        this.notes = b.notes;
        // Deep copy lines to prevent external mutation from affecting totals
        List<OrderLine> copy = new ArrayList<>();
        for (OrderLine l : b.lines) {
            copy.add(new OrderLine(l.getSku(), l.getQuantity(), l.getUnitPriceCents()));
        }
        this.linesSnapshot = Collections.unmodifiableList(copy);
    }

    public String getId() { return id; }
    public String getCustomerEmail() { return customerEmail; }
    public Integer getDiscountPercent() { return discountPercent; }
    public boolean isExpedited() { return expedited; }
    public String getNotes() { return notes; }

    // Returns an unmodifiable deep copy so external modifications don't impact internal state
    public List<OrderLine> getLines() {
        List<OrderLine> copy = new ArrayList<>();
        for (OrderLine l : linesSnapshot) {
            copy.add(new OrderLine(l.getSku(), l.getQuantity(), l.getUnitPriceCents()));
        }
        return Collections.unmodifiableList(copy);
    }

    public int totalBeforeDiscount() {
        int sum = 0;
        for (OrderLine l : linesSnapshot) sum += l.getQuantity() * l.getUnitPriceCents();
        return sum;
    }

    public int totalAfterDiscount() {
        int base = totalBeforeDiscount();
        if (discountPercent == null) return base;
        return base - (base * discountPercent / 100);
    }

    public static Builder builder(String id, String customerEmail) {
        return new Builder(id, customerEmail);
    }

    public static final class Builder {
        private final String id;
        private final String customerEmail;
        private final List<OrderLine> lines = new ArrayList<>();
        private Integer discountPercent;
        private boolean expedited;
        private String notes;

        public Builder(String id, String customerEmail) {
            this.id = id;
            this.customerEmail = customerEmail;
        }

        public Builder addLine(OrderLine line) {
            Objects.requireNonNull(line, "line");
            lines.add(line);
            return this;
        }

        public Builder addLines(List<OrderLine> lines) {
            if (lines != null) for (OrderLine l : lines) addLine(l);
            return this;
        }

        public Builder discountPercent(Integer discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder expedited(boolean expedited) {
            this.expedited = expedited;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Order build() {
            if (!PricingRules.isValidEmail(customerEmail)) {
                throw new IllegalArgumentException("invalid email");
            }
            if (!PricingRules.isValidDiscount(discountPercent)) {
                throw new IllegalArgumentException("invalid discount");
            }
            if (lines.isEmpty()) {
                throw new IllegalStateException("order must have at least one line");
            }
            return new Order(this);
        }
    }
}
