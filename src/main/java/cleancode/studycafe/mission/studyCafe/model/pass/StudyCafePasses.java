package cleancode.studycafe.mission.studyCafe.model.pass;

import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafePass> passes;

    public StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = List.copyOf(passes);
    }

    public List<StudyCafePass> findByType(StudyCafePassType type) {
        return passes.stream()
            .filter(pass -> pass.hasSameTypeAs(type))
            .toList();
    }
}
