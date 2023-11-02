package order;

import item.InventoryItem;
import item.OrderItem;
import payment.Payment;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderID = 1;
    private int orderID;
    private List<OrderItem> orderItems;

    public Order() {
        this.orderID = nextOrderID++;
        this.orderItems = new ArrayList<>();
    }

    /* This method allows adding an item to the order with a specified quantity.
    It creates an OrderItem object representing the ordered item and adds it to the list of order items. */
    public void addItem(InventoryItem item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        orderItems.add(orderItem);
    }

    /* This method calculates the total cost of the order by summing up the total cost of each ordered item in the list.
    It returns a double representing the total cost. */
    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalCost += orderItem.getTotalCost();
        }
        return totalCost;
    }

    /* This method verifies if the provided payment is sufficient to cover the total cost of the order.
    If the payment amount is greater than or equal to the total cost, it returns true, indicating a successful payment authorization.
    Otherwise, it returns false. */
    public boolean processPayment(Payment payment) {
        double totalCost = calculateTotalCost();
        if (payment.getAmount() >= totalCost) {
            return true;
        }
        return false;
    }

    /* This method updates the inventory by reducing the quantities of items that were part of the order.
    It checks if the items in the order exist in the inventory, and if so, it updates their quantities. */
    public void updateInventory(List<InventoryItem> inventory) {
        for (OrderItem orderItem : orderItems) {
            InventoryItem inventoryItem = findItemInInventory(inventory, orderItem.getItem().getItemID());
            if (inventoryItem != null) {
                int newQuantity = inventoryItem.getQuantity() - orderItem.getQuantity();
                if (newQuantity >= 0) {
                    inventoryItem.setQuantity(newQuantity);
                }
            }
        }
    }

    /* A private method used to find an item in the inventory by its unique item ID.
    It returns the found InventoryItem object or null if the item is not found. */
    private InventoryItem findItemInInventory(List<InventoryItem> inventory, int itemID) {
        for (InventoryItem item : inventory) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    /* A getter method to retrieve the unique order ID of the order. */
    public int getOrderID() {
        return orderID;
    }
}
