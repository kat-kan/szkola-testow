package module3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NameInverterTest {
    private static final String EMPTY_STRING = "";
    private NameInverter nameInverter;

    @BeforeEach
    void createNameInverter() {
        nameInverter = new NameInverter();
    }

    @Test
    @DisplayName("Should throw exception when null is given")
    void shouldThrowExceptionWhenNullGiven() {
        //when
        Throwable thrown = catchThrowable(() -> nameInverter.invert(null));

        //then
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Should return empty string when empty string is given")
    void shouldReturnEmptyStringWhenEmptyStringGiven() {
        //when
        String inverted = nameInverter.invert("");

        //then
        assertThat(inverted).isEqualTo(EMPTY_STRING);
    }

    @Test
    @DisplayName("Should return empty string when spaces are given")
    void shouldReturnEmptyStringWhenSpacesGiven() {
        //when
        String inverted = nameInverter.invert("     ");

        //then
        assertThat(inverted).isEqualTo(EMPTY_STRING);
    }

    @Test
    @DisplayName("Should return first name when only first name is given")
    void shouldReturnFirstNameWhenOnlyFirstNameIsGiven() {
        //when
        String inverted = nameInverter.invert("Bogusław");

        //then
        assertThat(inverted).isEqualTo("Bogusław");
    }
}
