import java.util.InputMismatchException;

/**
 * A class to store a character and its morse code encoding in a MorseCode object.
 *
 * @author Abdul Rafi
 * @version 1.0
 */
public class MorseCode {
    /**
     * Stores the character as a char.
     */
    private final char character;

    /**
     * Stores the encoding as a String.
     */
    private final String encoding;

    /**
     * Constructor for the MorseCode class.
     * @param character char parameter for the character
     * @param encoding String parameter for the encoding
     */
    public MorseCode(char character, String encoding) throws InputMismatchException{

        // Checks to see if the character is valid and throws an Exception otherwise.
        try {
            int charAscii = (int) character;
            if(charAscii < 32 || charAscii > 90 || encoding == null || encoding.trim().length() == 0) {
                throw new InputMismatchException();
            }
        }catch (InputMismatchException ex1){
            System.out.printf("The character '%c' with conversion '%s' is invalid.%n", character, encoding);
            throw new InputMismatchException();
        }

        this.character = character;
        this.encoding = encoding;
    }

    /**
     * Gets the character from the MorseCode object.
     * @return char character member
     */
    public char getCharacter(){
        return character;
    }

    /**
     * Gets the encoding from the MorseCode object.
     * @return String encoding member
     */
    public String getEncoding(){
        return encoding;
    }
}
