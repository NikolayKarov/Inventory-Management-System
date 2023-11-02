package item;

import itemtype.Breakable;
import itemtype.Categorizable;
import itemtype.Perishable;
import itemtype.Sellable;

public abstract class AbstractItem implements Item, Categorizable,
        Breakable, Perishable, Sellable {

    private String name;
    private String category;
    private boolean breakable;
    private boolean perishable;
    private double price;

    public AbstractItem() {
    }

    public AbstractItem(String name) {
        this.name = name;
        this.category = "Uncategorized";
        this.breakable = false;
        this.perishable = false;
        this.price = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemDetails() {
        return "Name: " + name + "\nCategory: " + category + "\nPrice: " + price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void handleBreakage() {
        if (breakable) {
            System.out.println("Item " + name + " is broken. Handling breakage.");

        } else {
            System.out.println("Item " + name + " is not breakable.");
        }
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void handleExpiration() {
        if (perishable) {
            System.out.println("Item " + name + " has expired. Handling expiration.");

        } else {
            System.out.println("Item " + name + " is not perishable.");
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}

