package cleancode.studycafe.mission2.io.provider;

import cleancode.studycafe.mission2.model.pass.StudyCafePassType;
import cleancode.studycafe.mission2.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.mission2.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.mission2.provider.LockerPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("파일에서 락커 사용 가능한 이용권을 읽어오는 테스트")
class LockerPassFileReaderTest {

    @DisplayName("CSV 파일을 읽어 락커 이용권 목록을 반환한다")
    @Test
    void readLockerPassesFromCsvFile() {
        // given
        LockerPassProvider reader = new LockerPassFileReader();

        // when
        StudyCafeLockerPasses passes = reader.getLockerPasses();

        // then
        assertThat(passes).isNotNull();
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.0);
        assertThat(passes.findLockerPassBy(seatPass)).isNotEmpty();
    }

}
