package cleancode.studycafe.mission2.model.order;

import cleancode.studycafe.mission2.model.pass.StudyCafePassType;
import cleancode.studycafe.mission2.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.mission2.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("이용권 주문 및 금액 계산 테스트")
class StudyCafePassOrderTest {

    @DisplayName("락커 미포함 시간권 주문: 할인 없음")
    @Test
    void orderHourlyPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 13000, 0.0);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // then
        assertThat(order.getLockerPass()).isEmpty();
        assertThat(order.getTotalPrice()).isEqualTo(13000);
    }

    @DisplayName("락커 미포함 주간 2주권 주문: 10% 할인 적용")
    @Test
    void orderWeeklyPass_2Weeks() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // then
        assertThat(order.getLockerPass()).isEmpty();
        assertThat(order.getTotalPrice()).isEqualTo((int)(100000 - (100000 * 0.1)));
    }

    @DisplayName("락커 미포함 주간 12주권 주문: 15% 할인 적용")
    @Test
    void orderWeeklyPass_12Weeks() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // then
        assertThat(order.getLockerPass()).isEmpty();
        assertThat(order.getTotalPrice()).isEqualTo((int)(400000 - (400000 * 0.15)));
    }

    @DisplayName("락커 미포함 고정석 4주권 주문: 10% 할인 적용")
    @Test
    void orderNoLockerFixedPass_4Weeks() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // then
        assertThat(order.getLockerPass()).isEmpty();
        assertThat(order.getTotalPrice()).isEqualTo((int)(250000 - (250000 * 0.1)));
    }

    @DisplayName("락커 포함 고정석 4주권 주문: 10% 할인 적용")
    @Test
    void orderUseLockerFixedPass_4Weeks() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        // then
        assertThat(order.getLockerPass()).isPresent();
        assertThat(order.getTotalPrice()).isEqualTo((int)(250000 + 10000 - (250000 * 0.1)));
    }

    @DisplayName("락커 포함 고정석 12주권 주문: 15% 할인 적용")
    @Test
    void orderUseLockerFixedPass_12Weeks() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        // then
        assertThat(order.getLockerPass()).isPresent();
        assertThat(order.getTotalPrice()).isEqualTo((int)(700000 + 30000 - (700000 * 0.15)));
    }
}
