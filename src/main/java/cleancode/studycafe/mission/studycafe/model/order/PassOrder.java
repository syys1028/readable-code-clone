package cleancode.studycafe.mission.studycafe.model.order;

import cleancode.studycafe.mission.studycafe.model.pass.StudyCafeLockerPass;
import cleancode.studycafe.mission.studycafe.model.pass.StudyCafePass;

public record PassOrder(StudyCafePass pass, StudyCafeLockerPass lockerPass) {

    public int calculateTotalPrice() {
        int lockerPrice = lockerPass != null ? lockerPass.getPrice() : 0;
        return pass.calculateDiscountedPrice() + lockerPrice;
    }

}

