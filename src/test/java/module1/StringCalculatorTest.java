package module1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {
    StringCalculator calculator;

    @BeforeEach
    void createCalculator() {
        calculator = new StringCalculator();
    }

    @Test
    void shouldReturnZeroWhenEmptyInput() {
        Assertions.assertEquals(0, calculator.add(""));
    }

    @Test
    void shouldReturnNumberWhenOneNumberIsGiven() {
        Assertions.assertEquals(100, calculator.add("100"));
    }

    @Test
    void shouldReturnSumWhenTwoNumbersAreGiven() {
        Assertions.assertEquals(20, calculator.add("14,6"));
    }

    @Test
    void shouldReturnSumWhenFiveNumbersAreGiven() {
        Assertions.assertEquals(29, calculator.add("14,6,5,7,-3"));
    }

    @Test
    void shouldReturnNegativeWhenTwoNegativeNumbersAreGiven() {
        Assertions.assertEquals(-10, calculator.add("-3,-7"));
    }

    @Test
    void shouldThrowIncorrectInputExceptionWhenLettersGiven() {
        Assertions.assertThrows(IncorrectInputDataFormatException.class,
                () -> calculator.add("x,y"));
    }

    @Test
    void shouldThrowIncorrectInputExceptionWhenDoubleGiven() {
        Assertions.assertThrows(IncorrectInputDataFormatException.class,
                () -> calculator.add("5.0"));
    }

}