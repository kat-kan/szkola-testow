package module1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class StringCalculatorTest {
    private StringCalculator calculator;

    private static Stream<Arguments> provideMultipleNumbers() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6,7", 28),
                Arguments.of("3,3,0", 6),
                Arguments.of("7,3,5,5,25", 45),
                Arguments.of("1,1,1,1,1,1,1,1,1,1", 10)
        );
    }

    private static Stream<Arguments> provideInputWithCustomDelimiters() {
        return Stream.of(
                Arguments.of("//;\n1;2", 3),
                Arguments.of("//|\n1|2|3", 6),
                Arguments.of("//sep\n2sep3", 5)
        );
    }

    private static Stream<Arguments> provideInvalidInputs() {
        return Stream.of(
                Arguments.of("-1,,2", "Number expected but ',' found at position 3\nNegative not allowed : -1"),
                Arguments.of("-1,,-2", "Number expected but ',' found at position 3\nNegative not allowed : -1,-2")
        );
    }

    @BeforeEach
    void createCalculator() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("Should return zero when empty input")
    void shouldReturnZeroWhenEmptyInput() {
        //when
        int result = calculator.add("");

        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return number when one number is given")
    void shouldReturnNumberWhenOneNumberIsGiven() {
        //when
        int result = calculator.add("100");

        //then
        assertThat(result).isEqualTo(100);
    }

    @Test
    @DisplayName("Should return sum when two numbers are given")
    void shouldReturnSumWhenTwoNumbersAreGiven() {
        //when
        int result = calculator.add("14,6");

        //then
        assertThat(result).isEqualTo(20);
    }

    @ParameterizedTest
    @MethodSource("provideMultipleNumbers")
    @DisplayName("Should return sum when multiple numbers are given")
    void shouldReturnSumWhenMultipleNumbersAreGiven(String input, int expected) {
        //when
        int result = calculator.add(input);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should return sum when numbers separated by newline are provided")
    void shouldReturnSumWhenNumbersSeparatedByNewlineAreProvided() {
        //when
        int result = calculator.add("1\n2,3");

        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("Should throw exception when two delimiters between numbers are provided")
    void shouldThrowExceptionWhenTwoDelimitersProvidedBetweenNumbers() {
        //when
        Throwable thrown = catchThrowable(() -> calculator.add("1,2,3,6,\n7"));

        //then
        String additionalDelimiter = "\\n";
        assertThat(thrown).hasMessageContaining(additionalDelimiter);
    }

    @Test
    @DisplayName("Should throw exception when number is missing in the last position")
    void shouldThrowExceptionWhenNumberIsMissingInTheLastPosition() {
        //when
        Throwable thrown = catchThrowable(() -> calculator.add("1,2,3,"));

        //then
        assertThat(thrown).hasMessageContaining("Number expected but EOF found");
    }

    @ParameterizedTest
    @MethodSource("provideInputWithCustomDelimiters")
    @DisplayName("Should return sum when numbers with custom delimiter are provided")
    void shouldReturnSumWhenNumbersWithCustomDelimiterAreProvided(String input, int expected) {
        //when
        int result = calculator.add(input);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1,2", "1,2,-4,-5,7", "0,3,-2"})
    @DisplayName("Should throw exception when negative numbers are given")
    void shouldThrowExceptionWhenNegativeNumbersAreGiven(String input) {
        //when
        Throwable thrown = catchThrowable(() -> calculator.add(input));

        //then
        assertThat(thrown).hasMessageContaining("Negative not allowed");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidInputs")
    @DisplayName("Should validate input for all possible errors and throw exception with all error messages")
    void shouldValidateInputForAllPossibleErrorsAndThrowExceptionWithAllErrorMessages(String input, String expected) {
        //when
        Throwable thrown = catchThrowable(() -> calculator.add(input));

        //then
        assertThat(thrown).hasMessageContaining(expected);
    }
}