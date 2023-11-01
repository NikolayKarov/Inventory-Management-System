import item.InventoryItem;
import order.Order;
import payment.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManagement {
    static Scanner scanner = new Scanner(System.in);
    private static List<InventoryItem> inventory = new ArrayList<>();

    private static final String FILE_PATH = "D:\\Nikolay\\InventoryManagementSystem\\src\\resources\\inventory.csv";
    private static int id;

    public void addItem() {
        System.out.println("--- Adding new item to the inventory ---");
        System.out.print("Item name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        InventoryItem newItem = new InventoryItem();
        newItem.setName(name);
        newItem.setItemID(++id);
        newItem.setQuantity(quantity);
        newItem.setCategory(category);
        newItem.setPrice(price);
        inventory.add(newItem);
        System.out.println("Item added successful to inventory with ID: " + newItem.getItemID());

        CSVFileManager.saveItemToCSV(FILE_PATH, inventory);
    }


    public void removeItem() {
       CSVFileManager.printInventory(CSVFileManager.getInventoryFromFile());
        CSVFileManager.removeItemFromCSV(FILE_PATH, id);
    }

    public void displayInventory() {
        System.out.println("--- Display Inventory ---");
       CSVFileManager.printInventory(CSVFileManager.getInventoryFromFile());
    }

    public void categorizeItem() {
        System.out.println("--- Categorize item ---");
        CSVFileManager.printInventory(CSVFileManager.getInventoryFromFile());
        System.out.print("Item ID to categorize: ");
        int itemID = scanner.nextInt();
        scanner.nextLine();


        for (InventoryItem item : CSVFileManager.getInventoryFromFile()) {
            if (item.getItemID() == itemID) {
                System.out.print("Enter new category: ");
                String newCategory = scanner.nextLine().trim();
                item.setCategory(newCategory);
                System.out.println("Item categorization updated.");
                return;
            }
        }
        System.out.println("Item not found with ID " + itemID);
    }

    public void placeOrder() {
        List<Order> orders = new ArrayList<>();

        while (true) {
            System.out.println("--- Place order ---");
            System.out.println("--- Select an option ---");
            System.out.println("1. Create a new order");
            System.out.println("2. View orders");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                Order order = makeOrder(CSVFileManager.getInventoryFromFile());
                if (order != null) {
                    double totalCost = order.calculateTotalCost();
                    Payment payment = createPayment(totalCost);

                    if (payment != null) {
                        if (order.processPayment(payment)) {
                            order.updateInventory(CSVFileManager.getInventoryFromFile());
                            orders.add(order);
                            System.out.println("Order placed successfully. Order ID: " + order.getOrderID());
                        } else {
                            System.out.println("Payment failed. Insufficient funds.");
                        }
                    }
                }
            } else if (choice == 2) {
                viewOrders(orders);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static Order makeOrder(List<InventoryItem> inventory) {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        System.out.println("Available items in the inventory:");
        CSVFileManager.printInventory(CSVFileManager.getInventoryFromFile());
        System.out.print("Enter the item ID to add to the order: ");
        int itemID = scanner.nextInt();
        if (itemID == 0) {
            System.out.println("Invalid id.");
        }

        InventoryItem selectedItem = findItemInInventory(inventory, itemID);

        if (selectedItem != null) {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (quantity > 0 && quantity <= selectedItem.getQuantity()) {
                order.addItem(selectedItem, quantity);
                System.out.println("Item added to the order.");
                System.out.println("Total price: " + order.calculateTotalCost());
            } else if (quantity <= 0) {
                System.out.println("Invalid quantity. Please enter a positive quantity.");
            } else {
                System.out.println("Insufficient stock. Please select a lower quantity.");
            }
        } else {
            System.out.println("Item not found. Please enter a valid item ID.");
        }

        return order;
    }

    private static Payment createPayment(double amount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the payment amount: $");
        double paymentAmount = scanner.nextDouble();
        scanner.nextLine();

        if (paymentAmount >= amount) {
            System.out.print("Enter the payment method (Credit Card / PayPal): ");
            String paymentMethod = scanner.nextLine();

            return new Payment(paymentAmount, paymentMethod);
        } else {
            System.out.println("Payment amount is insufficient.");
            return null;
        }
    }

    private static InventoryItem findItemInInventory(List<InventoryItem> inventory, int itemID) {
        for (InventoryItem item : inventory) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    private static void viewOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
            System.out.println("Orders:");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderID() + ", Total Cost: $" + order.calculateTotalCost());
            }
        }
    }
}

