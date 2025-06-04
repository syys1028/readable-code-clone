package cleancode.studycafe.mission.io;

import cleancode.studycafe.mission.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.model.StudyCafePass;
import cleancode.studycafe.mission.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StudyCafeFileHandler {

    private List<String[]> readCsvLines(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath)).stream()
                .map(line -> line.split(","))
                .toList();
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    public List<StudyCafePass> readStudyCafePasses() {
        return readCsvLines("src/main/resources/cleancode/studycafe/pass-list.csv").stream()
            .map(values -> StudyCafePass.of(
                StudyCafePassType.valueOf(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]),
                Double.parseDouble(values[3])
            ))
            .toList();
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        return readCsvLines("src/main/resources/cleancode/studycafe/locker.csv").stream()
            .map(values -> StudyCafeLockerPass.of(
                StudyCafePassType.valueOf(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2])
            ))
            .toList();
    }

}
