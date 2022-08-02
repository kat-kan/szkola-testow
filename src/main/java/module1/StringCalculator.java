package module1;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String[] splitNumbers = numbers.split("[,\n]");
            int result = 0;
            for (int i = 0; i < splitNumbers.length; i++) {
                try {
                    result += getIntFromString(splitNumbers[i]);
                } catch (NumberFormatException exception) {
                    int problematicIndex = numbers.indexOf("\n");
                    throw new IncorrectInputDataFormatException("Number expected but '\\n' found at position " + problematicIndex + ".");
                }

            }
/*            for (String number :
                    splitNumbers) {
                try {
                    result += getIntFromString(number);
                } catch (NumberFormatException exception) {
                    throw new IncorrectInputDataFormatException("Nieprawidłowy format danych. Oczekiwany format: liczby całkowite po przecinku");
                }
            }*/
            return result;
        }
    }

    private static int getIntFromString(String number) {
        return Integer.parseInt(number);
    }
}
