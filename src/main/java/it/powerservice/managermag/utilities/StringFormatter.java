package it.powerservice.managermag.utilities;

public class StringFormatter {
    public static String labelize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder formatted = new StringBuilder();


        formatted.append(Character.toUpperCase(input.charAt(0)));

        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                formatted.append(' ');
            }
            formatted.append(currentChar);
        }

        return formatted.toString();
    }
}
