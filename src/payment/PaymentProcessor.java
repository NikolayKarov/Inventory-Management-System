package payment;

import paymentmethod.CreditCardPayment;
import paymentmethod.PayPalPayment;

public class PaymentProcessor {
    public void processPayment(String paymentMethod, double amount) {
        try {
            authorizePayment(paymentMethod, amount);;
        } catch (Exception e) {
            System.out.println("An error occurred during payment processing: " + e.getMessage());
        }
    }

    private void authorizePayment(String paymentMethod, double amount) {
        if ("Credit Card".equalsIgnoreCase(paymentMethod)) {
            CreditCardPayment creditCardPayment = new CreditCardPayment();
            creditCardPayment.authorizePayment(amount);
        } else if ("PayPal".equalsIgnoreCase(paymentMethod)) {
            PayPalPayment payPalPayment = new PayPalPayment();
           payPalPayment.authorizePayment(amount);
        } else {
            System.out.println("Unsupported payment method: " + paymentMethod);

        }
    }
}
