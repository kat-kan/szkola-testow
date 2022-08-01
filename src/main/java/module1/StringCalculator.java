package module1;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String[] splitNumbers = numbers.split(",");
            int result = 0;
            for (String number :
                    splitNumbers) {
                try {
                    result += getIntFromString(number);
                } catch (NumberFormatException exception) {
                    throw new IncorrectInputDataFormatException("Nieprawidłowy format danych. Oczekiwany format: liczby całkowite po przecinku");
                }
            }
            return result;
        }
    }

    private static int getIntFromString(String number) {
        return Integer.parseInt(number);
    }
}
