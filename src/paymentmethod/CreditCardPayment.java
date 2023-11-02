package paymentmethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreditCardPayment implements PaymentMethod {

    String cardNumber;
    String cardHolder;
    String expirationDate;
    String cvv;

    public CreditCardPayment() {
    }

    public CreditCardPayment(String cardNumber, String cardHolder, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public void authorizePayment(double amount) {
        if (isValidCardNumber(cardNumber)) {
            System.out.println("Payment authorization successful !");
        } else {
            System.out.println("Invalid card number. Payment authorization failed.");
        }

        if (isValidExpirationDate(expirationDate)) {
            System.out.println("Payment authorization successful !");
        } else {
            System.out.println("Invalid expiration date or card is expired. Payment authorization failed.");
        }

        if (isValidCvv(cvv)) {
            System.out.println("Payment authorization successful !");
        } else {
            System.out.println("Invalid CVV. Payment authorization failed.");
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        cardNumber = cardNumber.replaceAll("\\D", "");

        if (cardNumber.length() != 16) {
            return false;
        }

        int sum = 0;
        boolean doubleDigit = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }
        return sum % 10 == 0;
    }

    private boolean isValidExpirationDate(String expirationDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            LocalDate parsedDate = LocalDate.parse(expirationDate, formatter);
            LocalDate currentDate = LocalDate.now();
            return !parsedDate.isBefore(currentDate);
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isValidCvv(String cvv) {
        if (!cvv.matches("\\d+")) {
            return false;
        }
        int cvvLength = cvv.length();
        return cvvLength == 3 || cvvLength == 4;
    }
}
