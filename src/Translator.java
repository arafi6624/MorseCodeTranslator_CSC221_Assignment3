import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Translator {
    private final ArrayList<MorseCode> listMorseCodes = new ArrayList<>();

    public Translator(String conversionFile){
        String line = "";
        try (Scanner keyboard = new Scanner(new File(conversionFile))){
            while (keyboard.hasNext()){
                try {
                    line = keyboard.nextLine();
                    if(!line.isEmpty()){
                        String []tokens = line.split(";");
                        MorseCode morseCode = new MorseCode(tokens[0].trim().charAt(0),tokens[1].trim());
                        listMorseCodes.add(morseCode);
                    }
                }catch (Exception ex1){
                    System.out.printf("Got an invalid line: %s%n", line);
                }
            }
        }catch (FileNotFoundException ex1){
            System.out.printf("Failed to open file: %s%n", conversionFile);
        }
    }

    public void printList(){
        System.out.println();
        for(int i = 0; i < listMorseCodes.size(); ++i){
            System.out.printf("'%c' => \"%s\"%n", listMorseCodes.get(i).getCharacter(), listMorseCodes.get(i).getEncoding());
        }
    }

    public MorseCode get(char ch){
        for(int i = 0; i < listMorseCodes.size(); ++i){
            if (listMorseCodes.get(i).getCharacter() == ch){
                return listMorseCodes.get(i);
            }
        }
        return null;
    }

    public void convert(String line){
        if(line != null && !line.isEmpty()){
            char []ch = line.toCharArray();
            for(int i = 0; i < ch.length; ++i){
                char chUpper = Character.toUpperCase(ch[i]);
                if(get(chUpper) != null) {
                    System.out.printf("%s ",get(chUpper).getEncoding());
                }else if(chUpper != ' '){
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
                if(!line.isEmpty()){
                    convert(line);
                }
            }
        } catch (FileNotFoundException ex1) {
            System.out.printf("Failed to open file: %s",fileName);
        }
    }
}
