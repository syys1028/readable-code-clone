package cleancode.studycafe.mission2.io;

import cleancode.studycafe.mission2.exception.AppException;
import cleancode.studycafe.mission2.model.pass.StudyCafePassType;
import cleancode.studycafe.mission2.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("이용권 타입 입력 테스트")
class InputHandlerTest {

    private void provideUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @DisplayName("1을 입력하면 HOURLY 타입을 반환한다")
    @Test
    void inputOneReturnHourly() {
        // given
        provideUserInput("1");
        InputHandler inputHandler = new InputHandler();

        // when
        StudyCafePassType result = inputHandler.getPassTypeSelectingUserAction();

        // then
        assertThat(result).isEqualTo(StudyCafePassType.HOURLY);
    }

    @DisplayName("잘못된 입력을 하면 AppException이 발생한다")
    @Test
    void returnException() {
        // given
        provideUserInput("999");
        InputHandler inputHandler = new InputHandler();

        // when & then
        assertThatThrownBy(inputHandler::getPassTypeSelectingUserAction)
            .isInstanceOf(AppException.class)
            .hasMessage("잘못된 입력입니다.");
    }

    @DisplayName("사용자가 2를 입력하면 리스트에서 두 번째 이용권을 반환한다")
    @Test
    void inputTwoReturnSecondPass() {
        // given
        provideUserInput("2");
        InputHandler inputHandler = new InputHandler();

        StudyCafeSeatPass pass1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 0.0);
        StudyCafeSeatPass pass2 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 2000, 0.1);
        StudyCafeSeatPass pass3 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 3, 3000, 0.15);
        List<StudyCafeSeatPass> passes = List.of(pass1, pass2, pass3);

        // when
        StudyCafeSeatPass selected = inputHandler.getSelectPass(passes);

        // then
        assertThat(selected).isEqualTo(pass2);
    }

    @DisplayName("사용자가 락커 이용권을 선택하면 true를 반환한다")
    @Test
    void selectYesReturnTrue() {
        // given
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        InputHandler inputHandler = new InputHandler();

        // when
        boolean result = inputHandler.getLockerSelection();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("사용자가 락커 이용권을 선택하지 않으면 false를 반환한다")
    @Test
    void selectNoReturnFalse() {
        // given
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        InputHandler inputHandler = new InputHandler();

        // when
        boolean result = inputHandler.getLockerSelection();

        // then
        assertThat(result).isFalse();
    }
}
