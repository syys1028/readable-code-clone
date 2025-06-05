package cleancode.studycafe.mission.studycafe.model.pass;

import java.util.List;

public class LockerPasses {
    private final List<StudyCafeLockerPass> passes;

    public LockerPasses(List<StudyCafeLockerPass> passes) {
        this.passes = List.copyOf(passes);
    }

    public StudyCafeLockerPass findMatchingFor(StudyCafePass pass) {
        return passes.stream()
            .filter(locker -> locker.matchesPass(pass))
            .findFirst()
            .orElse(null);
    }
}
