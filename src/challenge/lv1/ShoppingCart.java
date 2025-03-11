package challenge.lv1;

import java.util.HashMap;
import java.util.Map;

import static challenge.lv1.util.PriceFormatter.priceFormat;

public class ShoppingCart {

    /**
     * 장바구니 필드
     */
    private final Map<MenuItem, Integer> cart = new HashMap<>();

    /**
     * 장바구니에 선택된 MenuItem과 수량을 저장
     * @param menuItem  선택된 MenuItem
     * @param quantity  MenuItem의 수량
     */
    public void addCart(MenuItem menuItem, int quantity) {
        cart.put(menuItem, quantity);
        System.out.println(menuItem.getName() + " "+ quantity + "개를 장바구니에 추가 하였습니다.");
    }

    /**
     * 장바구니의 내역을 보여줌
     * 장바구니의 상세 내역과 담긴 상품 및 수량의 전체 금액을 포맷팅 하여 반환
     * @return 포맷팅 된 장바구니의 총 금액
     */
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

    /**
     * 장바구니 내역을 모두 삭제
     */
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
