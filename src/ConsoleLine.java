import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//By Manas Rawat (Cyource):
class ConsoleLine {

    // Cross-method variables declared;
    static Matcher matcher;
    static Matcher matcherdos;
    static Double parser;
    static String converter;
    static String result;
    static String[] list = { "*", "/", "+", "-" };
    static int integer = 0;

	/*
	 * Warning suppressed for not closing input scanner as it stops continuous
	 * console lines.
	 */

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        result = input.nextLine();

        // Loop;
        while (true) {

            // Code for logging word to console;
            if (result.matches("console_log\\_\".+\"")) {
                result = result.substring(13, result.length() - 1);
                integer = 1;
            } else if (result.matches("console_solve_\\d+(|\\.\\d+)\\*\\d+(|\\.\\d+)")) {
                // Calls method-1 for mathematical operations.
                par(0);

				/*
				 * Parses Integers + performs mathematical operation; Note: I
				 * only coded 2 available numbers per mathematical operation.
				 */
                parser = (Double.valueOf(matcher.group()) * Double.valueOf(matcherdos.group()));

                // Calls method-2 for mathematical operations.
                cal();
            } else if (result.matches("console_solve_\\d+(|\\.\\d+)\\/\\d+(|\\.\\d+)")) {

                par(1);

                parser = (Double.valueOf(matcher.group()) / Double.valueOf(matcherdos.group()));

                cal();
            } else if (result.matches("console_solve_\\d+(|\\.\\d+)\\+\\d+(|\\.\\d+)")) {

                par(2);

                parser = (Double.valueOf(matcher.group()) + Double.valueOf(matcherdos.group()));

                cal();
            } else if (result.matches("console_solve_\\d+(|\\.\\d+)\\-\\d+(|\\.\\d+)")) {

                par(3);

                parser = (Double.valueOf(matcher.group()) - Double.valueOf(matcherdos.group()));

                cal();
            } else if (result.matches("console_log_\\d+(|\\.\\d+)")) {

                if (result.contains(".")) {
                    matcher = Pattern.compile("\\d+\\.\\d+").matcher(result);
                    matcher.find();
                } else {
                    matcher = Pattern.compile("\\d+").matcher(result);
                    matcher.find();
                }
                parser = (Double.parseDouble(matcher.group()));

                cal();
            }

            // Checks if input recognised = true/false;
            if (integer == 0) {
                if (result.matches("")) {
                } else {
                    System.out.println("NOT RECOGNISED");
                }
            } else {
                System.out.println(result);
            }
            integer = 0;
            result = input.nextLine();
        }
    }

    static void cal() {

        // Converts to String
        converter = parser.toString();
        if (converter.matches("\\d+.0")) {
            String subString = converter.substring(0, converter.length() - 2);
            result = subString;
        } else {
            result = converter;
        }

        integer = 1;
    }

    static void par(int i) {

        // Splits Line;
        String[] splitter = result.split(Pattern.quote("solve_"));

        String split = splitter[1];

        String[] sub = split.split(Pattern.quote(list[i]));

        String part = sub[0];
        String partdos = sub[1];

        // Checks if integer-1 is a decimal or not;
        if (part.contains(".")) {
            matcher = Pattern.compile("\\d+\\.\\d+").matcher(part);
            matcher.find();
        } else {
            matcher = Pattern.compile("\\d+").matcher(part);
            matcher.find();
        }

        // Checks if integer-2 is a decimal or not.
        if (partdos.contains(".")) {
            matcherdos = Pattern.compile("\\d+\\.\\d+").matcher(partdos);
            matcherdos.find();
        } else {
            matcherdos = Pattern.compile("\\d+").matcher(partdos);
            matcherdos.find();
        }
    }
}
