package challenge.lv2;

import java.util.Objects;

public class MenuItem {

    private final String name;
    private final long price;
    private final String description;

    private int quantity;

    public MenuItem(String name, long price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return getPrice() == menuItem.getPrice() && Objects.equals(getName(), menuItem.getName()) && Objects.equals(getDescription(), menuItem.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getDescription());
    }
}
