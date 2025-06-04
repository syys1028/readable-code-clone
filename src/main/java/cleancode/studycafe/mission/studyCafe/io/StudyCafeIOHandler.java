package cleancode.studycafe.mission.studyCafe.io;

import cleancode.studycafe.mission.studyCafe.model.pass.PassOrder;
import cleancode.studycafe.mission.studyCafe.model.order.StudyCafeLockerPass;
import cleancode.studycafe.mission.studyCafe.model.order.StudyCafePass;
import cleancode.studycafe.mission.studyCafe.model.order.StudyCafePassType;

import java.util.List;

public class StudyCafeIOHandler {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void showStartupMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }

    public void showPassOrderSummary(PassOrder passOrder) {
        outputHandler.showPassOrderSummary(passOrder);
    }

    public StudyCafePassType askPassTypeSelecting() {
        outputHandler.showPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafePass askPassSelecting(List<StudyCafePass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    public boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate) {
        outputHandler.askLockerPass(lockerPassCandidate);
        return inputHandler.getLockerSelection();
    }
}
