package module1;

public class StringCalculator {

    public int add(String numbers) {
        int result = 0;
        if (!numbers.isEmpty()) {
            if (numbers.endsWith(",")) {
                throw new IncorrectInputDataFormatException("Number expected but EOF found");
            } else {
                String delimiterRegex = "[,\n]";
                if (numbers.startsWith("//")) {
                    numbers = numbers.substring(2);
                    String[] separatedDelimiterAndNumbers = numbers.split("\n");
                    delimiterRegex = getDelimiterRegex(separatedDelimiterAndNumbers[0]);
                    numbers = separatedDelimiterAndNumbers[1];
                }
                result = calculateResult(numbers, delimiterRegex);
            }
        }
        return result;
    }

    private static int getIntFromString(String number) {
        return Integer.parseInt(number);
    }

    private String getDelimiterRegex(String customDelimiter) {
        return customDelimiter.matches("[^a-zA-Z]") ? "[" + customDelimiter + "]" : customDelimiter;
    }

    private int calculateResult(String numbers, String delimiterRegex) {
        String[] splitNumbers = numbers.split(delimiterRegex);
        int result = 0;
        for (String number :
                splitNumbers) {
            try {
                result += getIntFromString(number);
            } catch (NumberFormatException exception) {
                int newlineIndex = numbers.indexOf("\n");
                throw new IncorrectInputDataFormatException("Number expected but '\\n' found at position " + newlineIndex + ".");
            }
        }
        return result;
    }
}
