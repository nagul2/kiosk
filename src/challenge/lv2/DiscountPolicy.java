package challenge.lv2;

public enum DiscountPolicy {

    PATRIOT(1,10),
    SOLDIER(2,5),
    STUDENT(3,3),
    BASIC(4,0)
    ;

    private int input;
    private int discountRate;

    DiscountPolicy(int input, int discountRate) {
        this.input = input;
        this.discountRate = discountRate;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public int getInput() {
        return input;
    }

    public long discount(long price) {
        return price * discountRate / 100;
    }
}
