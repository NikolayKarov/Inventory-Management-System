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

    /* This method is required to implement the PaymentMethod interface.
    It attempts to authorize a payment for a specified amount using the credit card information.
    It checks the validity of the card number, expiration date, and CVV.
    If all the checks pass, it prints "Payment authorization successful!" to indicate a successful authorization.
    Otherwise, it provides specific error messages for each validation check that fails. */
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

    /* A private method that validates the credit card number.
    It checks whether the card number is 16 digits long and verifies its validity using the Luhn algorithm,
    which is a common checksum formula for credit card numbers. */
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

    /* A private method that validates the expiration date of the credit card.
    It parses the provided expiration date and compares it with the current date to ensure
    that the card is not expired */
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

    /*  A private method that validates the CVV of the credit card.
    It checks if the CVV consists only of digits and has a length of either 3 or 4 characters,
    as per common credit card standards. */
    private boolean isValidCvv(String cvv) {
        if (!cvv.matches("\\d+")) {
            return false;
        }
        int cvvLength = cvv.length();
        return cvvLength == 3 || cvvLength == 4;
    }
}
