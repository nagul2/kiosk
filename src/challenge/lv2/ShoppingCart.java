package challenge.lv2;

import java.util.*;

import static challenge.util.PriceFormatter.priceFormat;

public class ShoppingCart {

    /**
     * 장바구니 필드 원래 수량은 객체 개념상 MenuItem에 있는것이 맞다
     */
    private final Map<MenuItem, Integer> cartMap= new HashMap<>();

    /**
     * 장바구니를 리스트로 구조 변경 진행
     */
    private final List<MenuItem> cart = new ArrayList<>();


    // 메뉴 아이템 안에 수량 존재
    // key -> 메뉴아이템.id() 벨류에 메뉴 아이템,

    /**
     * 장바구니에 선택된 MenuItem과 수량을 저장
     * 만약 장바구니에 MenuItem이 존재한다면 기존의 수량에 수량을 추가
     * @param menuItem  선택된 MenuItem
     * @param quantity  MenuItem의 수량
     */
    public void addCart(MenuItem menuItem, int quantity) {
        int value = 0;
        if (cart.containsKey(menuItem)) {
             value = cart.get(menuItem);
        }

        cart.put(menuItem, quantity + value);
        System.out.println(menuItem.getName() + " "+ quantity + "개를 장바구니에 추가 하였습니다.");
    }

    /**
     * 장바구니의 내역을 보여줌
     * 장바구니의 상세 내역과 담긴 상품 및 수량의 전체 금액을 포맷팅 하여 반환
     * @return 포맷팅 된 장바구니의 총 금액
     */
    public void showCart() {
        System.out.println("[Orders]");
        cart.keySet().stream()
                .forEach(menuItem ->
                        System.out.printf("%-18s | %d개 | 상품 합계: ₩ %10s | %s%n",
                        // itemPrice(): 수량이 반영된 개별 메뉴당 합계 가격
                        menuItem.getName(), cart.get(menuItem), priceFormat(itemPrice(menuItem)), menuItem.getDescription()));
        System.out.println();
    }

    /**
     * 장바구니의 구조를 Map으로 구현했므로 stream을 사용하면 더 비효율 적이게 됨
     * 키오스크이에서 선택된 MenuItem을 제거
     * @param menuItem 제거할 MenuItem
     */
    public void deleteOneCart(MenuItem menuItem) {
        cart.remove(menuItem);
    }
    /**
     * 과제의 요구사항에 따라 장바구니가 List인 경우 Stream API를 활용하여 장바구니의 특정 메뉴를 제거하는 방법도 함께 구현 해보기
     * 사용하지 않을 것이므로 private 메서드로 구현
     */
    private void deleteOneCartList(MenuItem menuItem) {
        MenuItem findMenuItem = cartList.stream()
                .filter(cartItem -> cartItem.equals(menuItem))
                .findFirst()
                .get();

        cartList.remove(findMenuItem);
    }

    /**
     * 장바구니 내역을 모두 삭제
     */
    public void deleteAllCart() {
        cart.clear();
    }

    public long getTotalPrice() {
        long totalPrice = cart.keySet().stream()
                .mapToLong(menuItem -> itemPrice(menuItem)).sum();
        System.out.println("[Total]");
        String priceFormat = priceFormat(totalPrice);
        System.out.println("₩ " + priceFormat);
        return totalPrice;
    }

    public String getDiscountPrice(long totalPrice, int discountNumber) {
        DiscountPolicy[] values = DiscountPolicy.values();
        DiscountPolicy discountPolicy = Arrays.stream(values)
                .filter(policy -> policy.getInput() == discountNumber)
                .findFirst().get();

        long discount = discountPolicy.discount(totalPrice);
        return priceFormat(totalPrice - discount);
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
