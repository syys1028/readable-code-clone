package cleancode.studycafe.mission.model;

public class StudyCafePass implements PassDisplay{

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public boolean isFixedType() {
        return this.passType == StudyCafePassType.FIXED;
    }

    public boolean hasSameTypeAs(StudyCafePassType type) {
        return this.passType == type;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int calculateDiscountPrice() {
        return (int) (price * getDiscountRate());
    }

    public int calculateDiscountedPrice() {
        return getPrice() - calculateDiscountPrice();
    }

    public int calculateTotalPriceWith(StudyCafeLockerPass lockerPass) {
        int lockerPrice = lockerPass != null ? lockerPass.getPrice() : 0;
        return price - calculateDiscountPrice() + lockerPrice;
    }

}
