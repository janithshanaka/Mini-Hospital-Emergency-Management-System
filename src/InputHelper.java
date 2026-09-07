import java.util.Scanner;

/**
 * Utility class to provide safe, robust console input parsing.
 * Prevents application crashes from invalid data types (e.g. non-numeric input)
 * and properly consumes newline characters to avoid Scanner skipping bugs.
 */
public class InputHelper {

    /**
     * Safely reads an integer from the user.
     * Keeps prompting until a valid integer is provided.
     *
     * @param scanner the active Scanner instance
     * @param prompt  the message to display to the user
     * @return the valid integer entered
     */
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid input! Please enter a valid integer.");
            }
        }
    }

    /**
     * Safely reads a positive integer (> 0) from the user.
     *
     * @param scanner the active Scanner instance
     * @param prompt  the message to display to the user
     * @return a positive integer
     */
    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            int value = readInt(scanner, prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("[Error] Value must be a positive integer greater than 0.");
        }
    }

    /**
     * Safely reads a non-empty string from the user.
     *
     * @param scanner the active Scanner instance
     * @param prompt  the message to display to the user
     * @return the trimmed non-empty String entered
     */
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("[Error] Input cannot be empty. Please try again.");
        }
    }

    /**
     * Prompts the user to press Enter to return or proceed.
     *
     * @param scanner the active Scanner instance
     */
    public static void pressEnterToContinue(Scanner scanner) {
        System.out.print("\nPress [Enter] to continue...");
        scanner.nextLine();
    }
}
