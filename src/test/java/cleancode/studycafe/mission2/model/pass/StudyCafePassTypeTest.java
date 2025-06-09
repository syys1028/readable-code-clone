package cleancode.studycafe.mission2.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("이용권 타입 확인 테스트")
class StudyCafePassTypeTest {

    @DisplayName("고정석만 사물함 사용 가능 타입이다")
    @Test
    void onlyFixedSeatIsLockerType() {
        assertThat(StudyCafePassType.FIXED.isLockerType()).isTrue();
        assertThat(StudyCafePassType.HOURLY.isLockerType()).isFalse();
        assertThat(StudyCafePassType.WEEKLY.isLockerType()).isFalse();
    }

}
