package cleancode.studycafe.mission2.io;

import cleancode.studycafe.mission2.exception.AppException;
import cleancode.studycafe.mission2.model.pass.StudyCafePassType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

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

}
