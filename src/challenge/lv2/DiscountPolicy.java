package challenge.lv2;

public enum DiscountPolicy {

    PATRIOT(1, 10, "국가유공자"),
    SOLDIER(2, 5, "군인"),
    STUDENT(3, 3, "학생"),
    BASIC(4, 0, "일반")
    ;

    private final int input;
    private final int discountRate;
    private final String description;

    DiscountPolicy(int input, int discountRate, String value) {
        this.input = input;
        this.discountRate = discountRate;
        this.description = value;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public int getInput() {
        return input;
    }

    public String getDescription() {
        return description;
    }

    /**
     * totalPrice에 할인율을 적용하여 할인금액을 반환하는 메서드를 Enum에서 제공
     *
     * @param totalPrice 할인전 금액
     * @return 할인 정책에 따른 할인 금액을 반환
     */
    public long discount(long totalPrice) {
        return totalPrice * discountRate / 100;
    }
}
