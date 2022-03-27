import java.util.Scanner;

/**
 * Unit test class for MorseCodeConvert.java
 * Class assumes that MorseCodes.txt is in the classpath, if not update the path accordingly
 *
 * @author Ayman Zeidan
 */

public class TestA3 {

    private static final String CONVERSIONS_FILE_NAME = "MorseCodes.txt";
    private static final String BAD_FILE_NAME = "Non_Existing_File.bad";

    private static final String SELECTION_MENU = String.format("%n%s%n  %s%n  %s%n  %s%n  %s%n  %s%n%s",
            "Select one of the following choices: ",
            "1. Print conversion codes",
            "2. Convert character.",
            "3. Convert string.",
            "4. Convert file.",
            "5. Exit.",
            "Choice(1-4): ");

    public static void main(String[] args) {

        // tests if the Translator can handle bad or not found files
        try {
            new Translator(BAD_FILE_NAME);
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            System.out.println();
        }

        try (Scanner scanner = new Scanner(System.in)) {

            // This is the correct instance with the valid file
            Translator morseCodeConvert = new Translator(CONVERSIONS_FILE_NAME);

            int choice = 0;
            // show menu and prompt for option selection
            while (choice != 5) {

                System.out.print(SELECTION_MENU);

                String userInput = scanner.next();

                choice = 0;

                // input range validation
                if (userInput.length() == 1 && userInput.charAt(0) >= '1' && userInput.charAt(0) <= '4')
                    choice = Character.getNumericValue(userInput.charAt(0));

                // clear input buffer
                scanner.nextLine();

                try {

                    switch (choice) {
                        case 1: // option 1 prints the char-to-Morse code conversion table
                            morseCodeConvert.printList();
                            break;
                        case 2: // option 2 prompts for a character and converts it to Morse code
                            // ensures the convert method can handle null and empty string inputs
                            morseCodeConvert.get('~');
                            System.out.print("Enter a character to convert: ");
                            char ch = scanner.next().charAt(0);
                            MorseCode morseCode = morseCodeConvert.get(Character.toUpperCase(ch));
                            if (morseCode == null)
                                System.out.printf("No conversion for '%c'%n", ch);
                            else
                                System.out.println(morseCode.getEncoding());
                            break;
                        case 3: // option 3 prompts for a line and converts it to Morse code
                            // ensures the convert method can handle null and empty string inputs
                            morseCodeConvert.convert(null);
                            morseCodeConvert.convert("");

                            System.out.print("Enter a line to convert: ");
                            String line = scanner.nextLine();
                            morseCodeConvert.convert(line);
                            break;
                        case 4: // option 4 prompts for a file and converts its content to Morse code
                            System.out.print("Enter the file name to convert: ");
                            String fileName = scanner.nextLine();
                            morseCodeConvert.convertFile(fileName);
                            break;
                        case 5: // option 5 is to exit the program
                            break;
                        default: // invalid input received
                            System.out.printf("%n\"%s\" is not a valid option. Try again!%n%n", userInput);
                            System.out.flush();
                    }
                } catch (Exception ex) {
                    System.out.printf("%s %s%n", ex.getClass().getName(), ex.getMessage());
                }
            }
        } catch (Exception e) {

            System.out.println("\n");
            e.printStackTrace();
        }

        System.out.println("\nProgram terminated");
    }
}