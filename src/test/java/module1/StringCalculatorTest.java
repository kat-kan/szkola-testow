package module1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class StringCalculatorTest {
    private StringCalculator calculator;

    private static Stream<Arguments> provideMultipleNumbers() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6,7",28),
                Arguments.of("-3,3,0",0),
                Arguments.of("-7,-3,-5,-5,25", 5),
                Arguments.of("1,1,1,1,1,1,1,1,1,1", 10)
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

    @Test
    @DisplayName("Should return sum that is negative number when two negative numbers are given")
    void shouldReturnSumThatIsNegativeNumberWhenTwoNegativeNumbersAreGiven() {
        //when
        int result = calculator.add("-7,-3");

        //then
        assertThat(result).isEqualTo(-10);
    }

    @ParameterizedTest
    @MethodSource("provideMultipleNumbers")
    @DisplayName("Should return sum when multiple numbers are given")
    void shouldReturnSumWhenMultipleNumbersAreGiven(String input, int expected){
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
        Throwable thrown = catchThrowable(()-> calculator.add("1,2,3,6\n,7")) ;

        //then
        assertThat(thrown).hasMessageContaining("\\n");
    }
    
    /*    @Test
    void shouldThrowIncorrectInputExceptionWhenLettersGiven() {
        Assertions.assertThrows(IncorrectInputDataFormatException.class,
                () -> calculator.add("x,y"));
    }

    @Test
    void shouldThrowIncorrectInputExceptionWhenDoubleGiven() {
        Assertions.assertThrows(IncorrectInputDataFormatException.class,
                () -> calculator.add("5.0"));
    }*/

}