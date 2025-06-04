package cleancode.studycafe.mission;

import cleancode.studycafe.mission.exception.AppException;
import cleancode.studycafe.mission.io.InputHandler;
import cleancode.studycafe.mission.io.OutputHandler;
import cleancode.studycafe.mission.io.StudyCafeFileHandler;
import cleancode.studycafe.mission.io.StudyCafeIOHandler;
import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafePass selectedPass = selectPass();
            checkUseableLocker(selectedPass);

        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void checkUseableLocker(StudyCafePass selectedPass) {
        if (selectedPass.isFixedType()) {
            selectLocks(selectedPass);
        }
        else {
            showPassSummaryWithoutLocker(selectedPass);
        }
    }

    private void selectLocks(StudyCafePass selectedPass) {
        StudyCafeLockerPass lockerPass = selectLockerOption(selectedPass);
        if (lockerPass != null) {
            processLockerSelection(selectedPass, lockerPass);
        } else {
            showPassSummaryWithoutLocker(selectedPass);
        }
    }

    private void processLockerSelection(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        boolean isLockerSelected = ioHandler.askLockerPass(lockerPass);

        if (isLockerSelected) {
            ioHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            showPassSummaryWithoutLocker(selectedPass);
        }
    }

    private void showPassSummaryWithoutLocker(StudyCafePass pass) {
        ioHandler.showPassOrderSummary(pass, null);
    }

    private StudyCafePass selectPass() {
        StudyCafePassType selectedType = getSelectedPassType();
        List<StudyCafePass> passTypeInfo = findPassByType(selectedType);
        return getSelectedPass(passTypeInfo);
    }

    private StudyCafePassType getSelectedPassType() {
        return ioHandler.askPassTypeSelecting();
    }

    private StudyCafePass getSelectedPass(List<StudyCafePass> passTypeInfo) {
        return ioHandler.askPassSelecting(passTypeInfo);
    }

    private List<StudyCafePass> findPassByType(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.hasSameTypeAs(studyCafePassType))
            .toList();
    }

    private StudyCafeLockerPass selectLockerOption(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.stream()
            .filter(option -> option.matchesPass(selectedPass))
            .findFirst()
            .orElse(null);
    }

}
