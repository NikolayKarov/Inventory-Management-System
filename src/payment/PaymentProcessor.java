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

    /* This private method is responsible for authorizing the payment based on the chosen payment method.
    It takes the paymentMethod and amount as parameters. It supports two payment methods, "Credit Card" and "PayPal."
    Depending on the provided paymentMethod, it creates an instance of the corresponding payment method class (either CreditCardPayment or PayPalPayment)
    and calls the authorizePayment method of that class to initiate the payment authorization process.
    If an unsupported payment method is provided, it displays an error message. */
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
