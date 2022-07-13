package module1;

public class StringCalculator {
    public static void main(String[] args) {
        System.out.println(add("10,3,4"));
        System.out.println(add(""));
        System.out.println(add("x,y,z"));
        System.out.println(add("2.0, 5.0"));

    }

    static int add(String input) {
        if (input.isEmpty()) {
            return 0;
        } else {
            String[] numbers = input.split(",");
            int result = 0;
            for (String number :
                    numbers) {
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
