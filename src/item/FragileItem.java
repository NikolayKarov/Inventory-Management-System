package item;

import itemtype.Breakable;

public class FragileItem extends InventoryItem implements Breakable {
    private double weight;

    public FragileItem(String name, int itemID, int quantity, double weight) {
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