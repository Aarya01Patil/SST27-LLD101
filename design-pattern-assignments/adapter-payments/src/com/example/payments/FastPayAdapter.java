package com.example.payments;

final class FastPayAdapter implements PaymentGateway {
    private final FastPayClient client;
    FastPayAdapter(FastPayClient client) { this.client = client; }
    @Override public String charge(String customerId, int amountCents) {
        return client.payNow(customerId, amountCents);
    }
}


