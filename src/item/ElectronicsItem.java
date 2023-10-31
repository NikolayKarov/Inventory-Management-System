package item;

public class ElectronicsItem extends InventoryItem {
    private String brand;
    private String model;

    public ElectronicsItem(String name, int itemID, int quantity, String brand, String model) {
        super(name, itemID, quantity);
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
