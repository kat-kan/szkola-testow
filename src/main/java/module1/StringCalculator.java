package module1;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if (numbers.endsWith(",")) {
            throw new IncorrectInputDataFormatException("Number expected but EOF found");
        }
        else {
            return calculateResult(numbers);
        }
    }

    private int calculateResult(String numbers) {
        String[] splitNumbers = numbers.split("[,\n]");
        int result = 0;
        for (String number:
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

    private static int getIntFromString(String number) {
        return Integer.parseInt(number);
    }
}
