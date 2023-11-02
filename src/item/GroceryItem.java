package item;

import itemtype.Perishable;

public class GroceryItem extends InventoryItem implements Perishable {
    private double weight;


    public GroceryItem(String name, int itemID, int quantity, double weight) {
        super(name, itemID, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
