import java.util.Scanner;

public class CommandLineInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        InventoryManagement management = new InventoryManagement();

        while (true) {
            System.out.println("--- Inventory Management System ---");
            System.out.println("1. Add item.Item");
            System.out.println("2. Remove item.Item");
            System.out.println("3. Display Inventory");
            System.out.println("4. Categorize item.Item");
            System.out.println("5. Place order.Order");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    management.addItem();
                    break;
                case 2:
                    management.removeItem();
                    break;
                case 3:
                    management.displayInventory();
                    break;
                case 4:
                    management.categorizeItem();
                    break;
                case 5:
                    management.placeOrder();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}