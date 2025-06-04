package cleancode.studycafe.mission.studyCafe.model.order;

import cleancode.studycafe.mission.studyCafe.view.PassDisplay;

public class StudyCafeLockerPass implements PassDisplay {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public boolean matchesPass(StudyCafePass pass) {
        return this.passType == pass.getPassType()
            && this.duration == pass.getDuration();
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

}
