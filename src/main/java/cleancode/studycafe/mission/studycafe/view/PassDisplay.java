package cleancode.studycafe.mission.studycafe.view;

import cleancode.studycafe.mission.studycafe.model.pass.StudyCafePassType;

public interface PassDisplay {
    StudyCafePassType getPassType();
    int getDuration();
    int getPrice();
}
