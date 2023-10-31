package itemtype;

public interface Perishable {
    boolean isPerishable();

    void handleExpiration();
}
