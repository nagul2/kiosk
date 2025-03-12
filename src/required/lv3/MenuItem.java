package required.lv3;

public class MenuItem {

    String name;
    long price;
    String description;

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
}
