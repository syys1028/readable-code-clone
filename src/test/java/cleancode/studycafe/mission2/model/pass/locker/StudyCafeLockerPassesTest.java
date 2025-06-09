package cleancode.studycafe.mission2.model.pass.locker;

import cleancode.studycafe.mission2.model.pass.StudyCafePassType;
import cleancode.studycafe.mission2.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("락커 이용권 목록 필터링 테스트")
class StudyCafeLockerPassesTest {

    @DisplayName("주어진 좌석 이용권과 기간이 같은 락커 이용권을 찾는다")
    @Test
    void findMatchingLockerPass() {
        // given
        StudyCafeLockerPass locker1 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
        StudyCafeLockerPass locker2 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 6, 30000);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(locker1, locker2));

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);

        // when
        Optional<StudyCafeLockerPass> result = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(locker1);
    }

    @DisplayName("조건에 맞는 락커 이용권이 없으면 빈 Optional을 반환한다")
    @Test
    void returnEmptyWhenNoMatch() {
        // given
        StudyCafeLockerPass locker = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 6, 10000);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(locker));

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);

        // when
        Optional<StudyCafeLockerPass> result = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(result).isEmpty();
    }
}
