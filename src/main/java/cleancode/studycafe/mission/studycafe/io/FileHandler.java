package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.mission.studycafe.model.pass.LockerPasses;
import cleancode.studycafe.mission.studycafe.model.pass.StudyCafePasses;

public interface FileHandler {
    StudyCafePasses readStudyCafePasses();
    LockerPasses readLockerPasses();
}
