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

    public void addItem(InventoryItem item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        orderItems.add(orderItem);
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalCost += orderItem.getTotalCost();
        }
        return totalCost;
    }

    public boolean processPayment(Payment payment) {
        double totalCost = calculateTotalCost();
        if (payment.getAmount() >= totalCost) {
            return true;
        }
        return false;
    }

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

    private InventoryItem findItemInInventory(List<InventoryItem> inventory, int itemID) {
        for (InventoryItem item : inventory) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    public int getOrderID() {
        return orderID;
    }
}
