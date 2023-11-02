package paymentmethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayPalPayment implements PaymentMethod {

    String emailAccount;

    public PayPalPayment() {
    }

    public PayPalPayment(String accountEmail) {
        this.emailAccount = accountEmail;
    }

    /* This method is required to implement the PaymentMethod interface.
    It attempts to authorize a payment for a specified amount using the PayPal account email address.
    It checks the validity of the email address.
    If the email address is in a valid format, it prints "Payment authorization successful!"
    to indicate a successful authorization. Otherwise, it provides an error message to indicate
    that the email address is invalid. */
    @Override
    public void authorizePayment(double amount) {
        if (isValidEmail(emailAccount)) {
            System.out.println("Payment authorization successful !");
        } else {
            System.out.println("Invalid email account. Payment authorization failed.");
        }
    }

    /* A private method that validates the email account. It uses regular expressions to check whether the provided email address matches a valid email format.
    It returns true if the email is in a valid format and false if it is not. */
    private boolean isValidEmail(String emailAccount) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(emailAccount);
        return matcher.matches();
    }
}
