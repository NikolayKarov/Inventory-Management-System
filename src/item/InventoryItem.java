package item;

public class InventoryItem extends AbstractItem {
    private int itemID;
    private int quantity;

    private double price;

    public InventoryItem() {

    }

    public InventoryItem(String name, int itemID, int quantity) {
        super(name);
        this.itemID = itemID;
        this.quantity = quantity;

    }


    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double calculateValue() {
        return quantity * getItemPrice();
    }

    @Override
    public String getItemDescription() {
        return String.format("Item id: " + this.getItemID() + ", " + "Name: " + this.getName() + ", " + "Category: " + this.getCategory()
                + ", " + "Price: " + this.getPrice() + ", " + "Quantity: " + this.getQuantity());
    }

    @Override
    public void setItemPrice() {

    }

    @Override
    public double getItemPrice() {
        return getPrice();
    }
}
