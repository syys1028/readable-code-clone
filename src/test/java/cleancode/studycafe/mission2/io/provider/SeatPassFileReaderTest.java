package cleancode.studycafe.mission2.io.provider;

import cleancode.studycafe.mission2.model.pass.StudyCafePassType;
import cleancode.studycafe.mission2.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.mission2.provider.SeatPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("파일에서 이용권을 읽어오는 테스트")
class SeatPassFileReaderTest {
    @DisplayName("CSV 파일을 읽어 이용권 목록을 반환한다")
    @Test
    void readPassesFromCsvFile() {
        // given
        SeatPassProvider reader = new SeatPassFileReader();

        // when
        StudyCafeSeatPasses passes = reader.getSeatPasses();

        // then
        assertThat(passes).isNotNull();
        assertThat(passes.findPassBy(StudyCafePassType.HOURLY)).isNotEmpty();
    }

}
