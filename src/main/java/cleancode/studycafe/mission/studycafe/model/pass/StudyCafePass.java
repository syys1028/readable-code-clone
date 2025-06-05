package cleancode.studycafe.mission.studycafe.model.pass;

import cleancode.studycafe.mission.studycafe.view.PassDisplay;

public class StudyCafePass implements PassDisplay {

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

    public boolean isFixedType() {
        return this.passType == StudyCafePassType.FIXED;
    }

    public boolean hasSameTypeAs(StudyCafePassType type) {
        return this.passType == type;
    }

    public int calculateDiscountPrice() {
        return (int) (price * getDiscountRate());
    }

    public int calculateDiscountedPrice() {
        return getPrice() - calculateDiscountPrice();
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}
