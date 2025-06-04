package cleancode.studycafe.mission.studyCafe.view;

import cleancode.studycafe.mission.studyCafe.model.pass.StudyCafePassType;

public interface PassDisplay {
    StudyCafePassType getPassType();
    int getDuration();
    int getPrice();
}
