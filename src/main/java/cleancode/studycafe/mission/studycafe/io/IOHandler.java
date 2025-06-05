package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.mission.studycafe.model.order.PassOrder;
import cleancode.studycafe.mission.studycafe.model.pass.StudyCafeLockerPass;
import cleancode.studycafe.mission.studycafe.model.pass.StudyCafePass;
import cleancode.studycafe.mission.studycafe.model.pass.StudyCafePassType;

import java.util.List;

public interface IOHandler {
    void showStartupMessage();
    void showSimpleMessage(String message);
    void showPassOrderSummary(PassOrder passOrder);
    StudyCafePassType askPassTypeSelecting();
    StudyCafePass askPassSelecting(List<StudyCafePass> passCandidates);
    boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate);
}
