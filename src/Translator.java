import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class to translate plain text into morse code. It has the following members:
 * 1. listModeCodes
 * 2. printList()
 * 3. get()
 * 4. convert()
 * 5. convertFile()
 *
 * @author Abdul Rafi
 * @version 1.0
 */
public class Translator {
    /**
     * Stores a list of MorseCode objects in an ArrayList.
     */
    private final ArrayList<MorseCode> listMorseCodes = new ArrayList<>();

    /**
     * Constructor for the Translator class.
     * @param conversionFile String parameter for the file name
     */
    public Translator(String conversionFile){
        String line = "";
        try (Scanner keyboard = new Scanner(new File(conversionFile))){
            while (keyboard.hasNext()){
                try {
                    line = keyboard.nextLine();

                    // Checks to see if the line is empty and if not, tries to tokenize the string and
                    // create a MorseCode object from the character and encoding.
                    if(!line.isEmpty()){
                        String []tokens = line.split(";");
                        MorseCode morseCode = new MorseCode(tokens[0].trim().charAt(0),tokens[1].trim());
                        listMorseCodes.add(morseCode);
                    }
                }catch (InputMismatchException ex1){} // Ignores any InputMismatchException thrown from MorseCode.
                catch (Exception ex2){
                    System.out.printf("Got an invalid line: %s%n", line); // Prints an exception message invalid lines.
                }
            }
        }catch (FileNotFoundException ex1){
            System.out.printf("Failed to open file: %s%n", conversionFile); // Prints an exception message if the file wasn't able to open.
        }
    }

    /**
     * Prints the contents of listMorseCodes.
     */
    public void printList(){
        System.out.println();
        for(int i = 0; i < listMorseCodes.size(); ++i){
            System.out.printf("'%c' => \"%s\"%n", listMorseCodes.get(i).getCharacter(), listMorseCodes.get(i).getEncoding());
        }
    }

    /**
     * Searches for the MorseCode object with the character ch in listMorseCodes and returns it if found.
     * @param ch char parameter for the character
     * @return MorseCode object with the character ch
     */
    public MorseCode get(char ch){
        for(int i = 0; i < listMorseCodes.size(); ++i){
            // Checks if the character of the MorseCode object is the same as ch.
            if (listMorseCodes.get(i).getCharacter() == ch){
                return listMorseCodes.get(i);
            }
        }
        return null; // Returns null if no MorseCode object with the character ch was found in listMorseCodes.
    }

    /**
     * Converts a string to morse code.
     * @param line String parameter to translate
     */
    public void convert(String line){
        // Checks to see if the line is not empty or null.
        if(line != null && !line.isEmpty()){
            char []ch = line.trim().toCharArray(); // Creates a character Array with all the characters in the line.
            for(int i = 0; i < ch.length; ++i){
                char chUpper = Character.toUpperCase(ch[i]);

                // Checks to see if the character is in listMorseCodes and retrieves its MorseCode object.
                if(get(chUpper) != null) {
                    System.out.printf("%s ",get(chUpper).getEncoding()); // Prints the encoding from the MorseCode object.
                } else if(chUpper != ' '){ // Checks to see if the character is empty and if so, prints a question mark.
                    System.out.print("? ");
                }
            }
            System.out.print("\n");
        }
    }

    public void convertFile(String fileName){
        String line = "";
        try(Scanner keyboard = new Scanner(new File(fileName))){
            while(keyboard.hasNext()){
                line = keyboard.nextLine();

                // Checks to see if the line is not empty and if so, invokes the convert() method to translate the string to morse code.
                if(!line.isEmpty()){
                    convert(line);
                }
            }
        } catch (FileNotFoundException ex1) {
            System.out.printf("Failed to open file: %s",fileName); // Prints an exception message if the file wasn't able to open.
        }
    }
}
