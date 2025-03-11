package challenge.lv1;

import java.util.HashMap;
import java.util.Map;

import static challenge.lv1.util.PriceFormatter.priceFormat;

public class ShoppingCart {

    private final Map<MenuItem, Integer> cart = new HashMap<>();

    /**
     * 장바구니에 추가
     * @param menuItem
     * @param quantity
     */
    public void addCart(MenuItem menuItem, int quantity) {
        cart.put(menuItem, quantity);
        System.out.println(menuItem.getName() + " "+ quantity + "개를 장바구니에 추가 하였습니다.");
    }

    public String showCart() {
        long totalPrice = 0;
        System.out.println("[Orders]");
        for (MenuItem menuItem : cart.keySet()) {
            System.out.printf("%-18s | %d개 | 상품 합계: ₩ %10s | %s%n",
                    // itemPrice(): 수량이 반영된 개별 메뉴당 합계 가격
                    menuItem.getName(), cart.get(menuItem), priceFormat(itemPrice(menuItem)), menuItem.getDescription());
            totalPrice += itemPrice(menuItem);
        }
        System.out.println();
        System.out.println("[Total]");
        String priceFormat = priceFormat(totalPrice);
        System.out.println("₩ " + priceFormat);
        return priceFormat;
    }

    public void deleteAllCart() {
        cart.clear();
    }

    /**
     * 장바구니에 담긴 각 MenuItem의
     * @param menuItem  장바구니에 담긴 MenuItem
     * @return MenuItem의 수량 * 단가
     */
    private long itemPrice(MenuItem menuItem) {
        return cart.get(menuItem) * menuItem.getPrice();
    }


}
