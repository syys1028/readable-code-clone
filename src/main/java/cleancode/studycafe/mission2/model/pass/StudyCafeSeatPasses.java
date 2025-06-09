package cleancode.studycafe.mission2.model.pass;

import java.util.List;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> passes;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public List<StudyCafeSeatPass> findPassBy(StudyCafePassType studyCafePassType) {     // #2-2 check
        return passes.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
            .toList();
    }

}
