package cleancode.studycafe.mission;

import cleancode.studycafe.mission.exception.AppException;
import cleancode.studycafe.mission.io.InputHandler;
import cleancode.studycafe.mission.io.OutputHandler;
import cleancode.studycafe.mission.io.StudyCafeFileHandler;
import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePass selectedPass = selectPass();
            checkUseableLocker(selectedPass);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
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
        outputHandler.askLockerPass(lockerPass);
        boolean isLockerSelected = inputHandler.getLockerSelection();

        if (isLockerSelected) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            showPassSummaryWithoutLocker(selectedPass);
        }
    }

    private void showPassSummaryWithoutLocker(StudyCafePass pass) {
        outputHandler.showPassOrderSummary(pass, null);
    }

    private StudyCafePass selectPass() {
        StudyCafePassType selectedType = getSelectedPassType();
        List<StudyCafePass> passTypeInfo = findPassByType(selectedType);
        return getSelectedPass(passTypeInfo);
    }

    private StudyCafePassType getSelectedPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass getSelectedPass(List<StudyCafePass> passTypeInfo) {
        outputHandler.showPassListForSelection(passTypeInfo);
        return inputHandler.getSelectPass(passTypeInfo);
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
