package module3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameInverterTest {
    @Test
    @DisplayName("Should throw exception when null is given")
    void shouldThrowExceptionWhenNullGiven() {
        //given
        NameInverter nameInverter = new NameInverter();

        //then
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> nameInverter.invert(null));
    }
}
