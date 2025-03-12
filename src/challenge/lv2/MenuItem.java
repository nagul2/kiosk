package challenge.lv2;

import java.util.Objects;

public class MenuItem {

    /**
     * 상품 이름
     */
    private final String name;

    /**
     * 상품 단가
     */
    private final long price;

    /**
     * 상품 설명
     */
    private final String description;

    /**
     * 장바구니에 담은 수량
     */
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

    public int getQuantity() {
        return quantity;
    }

    /**
     * 수량 추가, 음수로 들어오더라도 절대값으로 값을 추가 되도록 예외 로직 적용
     * @param quantity 장바구니에 등록한 아이템 수량
     */
    public void addQuantity(int quantity) {
        this.quantity += Math.abs(quantity);
    }

    /**
     * 상품의 수량과 단가를 곱한 단일 상품의 금액 반환
     * @return 단가 * 수량
     */
    public long itemPrice() {
        return quantity * price;
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
