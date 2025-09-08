package com.example.payments;

final class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient client;
    SafeCashAdapter(SafeCashClient client) { this.client = client; }
    @Override public String charge(String customerId, int amountCents) {
        return client.createPayment(amountCents, customerId).confirm();
    }
}


