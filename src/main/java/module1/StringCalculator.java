package module1;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    private static int getIntFromString(String number) {
        return Integer.parseInt(number);
    }

    public int add(String numbers) {
        int result = 0;
        if (!numbers.isEmpty()) {
            validateInput(numbers);
            String[] split = splitByDelimiter(numbers);
            result = calculateResult(split);
        }
        return result;
    }

    private int calculateResult(String[] numbers) {
        int result = 0;
        for (String number :
                numbers) {
            try {
                int intFromString = getIntFromString(number);
                result += intFromString;
            } catch (NumberFormatException exception) {
                throw new IncorrectInputDataFormatException("Unknown error");
            }
        }
        return result;
    }

    private String getDelimiterRegex(String customDelimiter) {
        return customDelimiter.matches("[^a-zA-Z]") ? "[" + customDelimiter + "]" : customDelimiter;
    }


    private void validateInput(String numbers) {
        StringBuilder errorMessage = new StringBuilder();
        if (numbers.endsWith(",")) {
            errorMessage.append("Number expected but EOF found" + "\n");
        }
        String[] splitNumbers = splitByDelimiter(numbers);
        List<String> negativeNumbersIndexes = new ArrayList<>();
        for (String splitNumber : splitNumbers) {
            try {
                int intFromString = getIntFromString(splitNumber);
                if (intFromString < 0) {
                    negativeNumbersIndexes.add(String.valueOf(splitNumber));
                }
            } catch (NumberFormatException exception) {
                int position = -1;
                String unwantedDelimiter = "";
                if (numbers.contains(",,")) {
                    unwantedDelimiter = ",";
                    position = numbers.indexOf(",,") + 1;
                }
                if (numbers.contains("\n,")) {
                    unwantedDelimiter = "\\n";
                    position = numbers.indexOf("\n,") + 1;
                }
                if (numbers.contains(",\n")) {
                    unwantedDelimiter = "\\n";
                    position = numbers.indexOf(",\n") + 1;
                }
                if (numbers.contains("\n\n")) {
                    unwantedDelimiter = ",";
                    position = numbers.indexOf("\n\n") + 1;
                }
                errorMessage.append("Number expected but '").append(unwantedDelimiter).append("' found at position ").append(position).append("\n");

            }
        }
        if (negativeNumbersIndexes.size() > 0) {
            errorMessage.append("Negative not allowed : ").append(String.join(",", negativeNumbersIndexes));
        }
        if (errorMessage.length() > 0) {
            throw new IncorrectInputDataFormatException(errorMessage.toString());
        }
    }

    private String[] splitByDelimiter(String numbers) {
        String delimiterRegex = "[,\n]";
        if (numbers.startsWith("//")) {
            numbers = numbers.substring(2);
            String[] separatedDelimiterAndNumbers = numbers.split("\n");
            delimiterRegex = getDelimiterRegex(separatedDelimiterAndNumbers[0]);
            numbers = separatedDelimiterAndNumbers[1];
        }
        return numbers.split(delimiterRegex);
    }
}
