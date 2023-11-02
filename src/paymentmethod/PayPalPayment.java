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

    @Override
    public void authorizePayment(double amount) {
        if (isValidEmail(emailAccount)) {
            System.out.println("Payment authorization successful !");
        } else {
            System.out.println("Invalid email account. Payment authorization failed.");
        }
    }

    private boolean isValidEmail(String emailAccount) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(emailAccount);
        return matcher.matches();
    }
}
