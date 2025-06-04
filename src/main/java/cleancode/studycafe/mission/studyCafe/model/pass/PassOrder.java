package cleancode.studycafe.mission.studyCafe.model.pass;

import cleancode.studycafe.mission.studyCafe.model.order.StudyCafeLockerPass;
import cleancode.studycafe.mission.studyCafe.model.order.StudyCafePass;

public class PassOrder {
    private final StudyCafePass pass;
    private final StudyCafeLockerPass lockerPass;

    public PassOrder(StudyCafePass pass, StudyCafeLockerPass lockerPass) {
        this.pass = pass;
        this.lockerPass = lockerPass;
    }

    public int calculateTotalPrice() {
        int lockerPrice = lockerPass != null ? lockerPass.getPrice() : 0;
        return pass.getPrice() - pass.calculateDiscountPrice() + lockerPrice;
    }

    public StudyCafePass getPass() {
        return pass;
    }

    public StudyCafeLockerPass getLockerPass() {
        return lockerPass;
    }

}

