package cleancode.studycafe.mission2.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("이용권 목록 필터링 테스트")
class StudyCafeSeatPassesTest {

    @DisplayName("전체 이용권 목록에서 WEEKLY 타입의 이용권만 가져온다")
    @Test
    void findWeeklyPassesOnly() {
        // given
        StudyCafeSeatPass hourly = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 6, 9000, 0.0);
        StudyCafeSeatPass weekly1 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 3, 130000, 0.1);
        StudyCafeSeatPass weekly2 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15);
        StudyCafeSeatPass fixed = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);

        List<StudyCafeSeatPass> allPasses = List.of(hourly, weekly1, weekly2, fixed);
        StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(allPasses);

        // when
        List<StudyCafeSeatPass> result = seatPasses.findPassBy(StudyCafePassType.WEEKLY);

        // then
        assertThat(result).hasSize(2);
        assertThat(result).allMatch(pass -> pass.getPassType() == StudyCafePassType.WEEKLY);
    }

}
