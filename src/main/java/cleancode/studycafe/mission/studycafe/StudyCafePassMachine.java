package cleancode.studycafe.mission.studycafe;

import cleancode.studycafe.mission.studycafe.exception.AppException;
import cleancode.studycafe.mission.studycafe.io.FileHandler;
import cleancode.studycafe.mission.studycafe.io.IOHandler;
import cleancode.studycafe.mission.studycafe.io.StudyCafeFileHandler;
import cleancode.studycafe.mission.studycafe.io.StudyCafeIOHandler;
import cleancode.studycafe.mission.studycafe.model.order.PassOrder;
import cleancode.studycafe.mission.studycafe.model.pass.*;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final IOHandler ioHandler;
    private final FileHandler fileHandler;

    public StudyCafePassMachine() {
        this.ioHandler = new StudyCafeIOHandler();
        this.fileHandler = new StudyCafeFileHandler();
    }

    public void run() {
        try {
            ioHandler.showStartupMessage();

            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> lockerPass = selectLockerIfAvailable(selectedPass);
            showFinalSummary(selectedPass, lockerPass.orElse(null));
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectPass() {
        StudyCafePassType selectedType = getSelectedPassType();
        List<StudyCafePass> passTypeInfo = filterPassByType(selectedType);
        return getSelectedPass(passTypeInfo);
    }

    private StudyCafePassType getSelectedPassType() {
        return ioHandler.askPassTypeSelecting();
    }

    private StudyCafePass getSelectedPass(List<StudyCafePass> passTypeInfo) {
        return ioHandler.askPassSelecting(passTypeInfo);
    }

    private Optional<StudyCafeLockerPass> selectLockerIfAvailable(StudyCafePass selectedPass) {
        if (!selectedPass.isFixedType()) return Optional.empty();

        StudyCafeLockerPass lockerOption = findMatchingLocker(selectedPass);
        if (lockerOption == null) return Optional.empty();

        boolean selected = ioHandler.askLockerPass(lockerOption);
        return selected ? Optional.of(lockerOption) : Optional.empty();
    }

    private StudyCafeLockerPass findMatchingLocker(StudyCafePass selectedPass) {
        LockerPasses lockerPasses = fileHandler.readLockerPasses();
        return lockerPasses.findMatchingFor(selectedPass);
    }

    private void showFinalSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        PassOrder passOrder = new PassOrder(selectedPass, lockerPass);
        ioHandler.showPassOrderSummary(passOrder);
    }

    private List<StudyCafePass> filterPassByType(StudyCafePassType studyCafePassType) {
        StudyCafePasses studyCafePasses = fileHandler.readStudyCafePasses();
        return studyCafePasses.findByType(studyCafePassType);
    }

}
