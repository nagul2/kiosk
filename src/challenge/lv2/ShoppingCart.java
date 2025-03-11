package challenge.lv2;

import java.util.*;

import static challenge.util.PriceFormatter.priceFormat;

public class ShoppingCart {

    /**
     * 장바구니를 List로 구조 변경
     * 도전 LV1에는 Map<MenuItem, Integer>로 장바구니를 구현하여 수량을 장바구니에서 관리했지만,
     * 튜터링을 통해 객체 개념상 수량은 MenuItem에 있는 것이 맞다고 들어서 고민 끝에 구조를 전부 변경하기로 결정
     */
    private final List<MenuItem> cart = new ArrayList<>();

    /**
     * 장바구니에 선택된 MenuItem과 수량을 저장
     * 만약 장바구니에 MenuItem이 존재한다면 기존의 수량에 수량을 추가
     *
     * @param menuItem 선택된 MenuItem
     * @param quantity MenuItem의 수량
     */
    public void addCart(MenuItem menuItem, int quantity) {
        for (MenuItem item : cart) {
            if (item.equals(menuItem)) {
                item.addQuantity(quantity);
                System.out.println(menuItem.getName() + " " + menuItem.getQuantity() + " 개가 장바구니에 추가 되었습니다.");
                return;
            }
        }
        menuItem.addQuantity(quantity);
        cart.add(menuItem);
        System.out.println(menuItem.getName() + " " + quantity + " 개가 장바구니에 추가 되었습니다.");
    }

    /**
     * 장바구니의 내역을 Stream으로 순회하며 출력
     * 도전 LV1에서 전체 가격을 함께 출력했던 기능을 분리하였음.
     */
    public void showCart() {
        System.out.println("[Orders]");
        cart.stream().forEach(menuItem -> System.out.printf("%-18s | %d개 | 상품 합계: ₩ %10s | %s%n",
                // itemPrice(): 수량이 반영된 개별 메뉴당 합계 가격
                menuItem.getName(), menuItem.getQuantity(), priceFormat(menuItem.itemPrice()) , menuItem.getDescription()));
        System.out.println();
    }

    /**
     * 장바구니에 담긴 전체 금액을 포맷팅 하여 출력하고 long 타입으로 반환 (할인 전)
     * @return 장바구니의 할인 전 전체 금액
     */
    public long getTotalPrice() {
        long totalPrice = cart.stream()
                .mapToLong(MenuItem::itemPrice)
                .sum();

        System.out.println("[Total]");
        String priceFormat = priceFormat(totalPrice);
        System.out.println("₩ " + priceFormat);
        return totalPrice;
    }

    /**
     * 할인 정책이 적용되어 할인이 적용된 전체 금액을 반환
     * Enum의 values를 활용하여 Enum의 상수들을 꺼내고 stream()을 통해 입력받은 할인 정책과 동일한지 비교하여 맞는 할인정책을 적용
     *
     * @param totalPrice 할인 전 장바구니 전체 금액
     * @param discountNumber 입력받은 할인 정책 번호
     * @return 출력을 위한 포맷팅된 할인 적용된 총 금액
     */
    public String getDiscountPrice(long totalPrice, int discountNumber) {
        DiscountPolicy[] values = DiscountPolicy.values();
        DiscountPolicy discountPolicy = Arrays.stream(values)
                .filter(policy -> policy.getInput() == discountNumber)
                .findFirst().get();

        long discount = discountPolicy.discount(totalPrice);
        return priceFormat(totalPrice - discount);
    }

    /**
     * Stream API를 활용한 장바구니의 특정 메뉴 삭제
     * 장바구니의 아이템을 스트림으로 순회하여 매개변수의 menuItem과 비교하여 하나라도 맞으면 true를 반환
     * 스트림의 결과가 true인 경우에만 삭제 로직이 동작하고 false면 별도의 동작이 동작하도록 변경
     */
    private void deleteOneCartList(MenuItem menuItem) {

        boolean nullCheck = cart.stream().anyMatch(cartItem -> cartItem.equals(menuItem));

        if (!nullCheck) {
            System.out.println(menuItem.getName() + "이 장바구니에 없습니다.");
            return;
        }

        cart.remove(menuItem);
    }

    /**
     * 장바구니 내역을 모두 삭제
     */
    public void deleteAllCart() {
        cart.clear();
    }

}
