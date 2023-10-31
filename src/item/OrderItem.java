package item;

public class OrderItem {
    private InventoryItem item;
    private int quantity;

    public OrderItem(InventoryItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public InventoryItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCost() {
        return item.getPrice() * quantity;
    }
}
