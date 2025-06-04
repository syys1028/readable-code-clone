package cleancode.studycafe.mission.studyCafe.view;

import cleancode.studycafe.mission.studyCafe.model.order.StudyCafePassType;

public interface PassDisplay {
    StudyCafePassType getPassType();
    int getDuration();
    int getPrice();
}
