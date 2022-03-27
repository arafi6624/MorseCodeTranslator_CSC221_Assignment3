public class MorseCode {
    private final char character;
    private final String encoding;

    public MorseCode(char character, String encoding) throws AssertionError{

        try {
            int charAscii = (int) character;
            if(charAscii < 32 || charAscii > 90 || encoding == null || encoding.trim().length() == 0) {
                throw new Exception();
            }
        }catch(Exception ex1){
            System.out.printf("The character '%c' with conversion '%s' is invalid.%n", character, encoding);
        }

        this.character = character;
        this.encoding = encoding;

    }

    public char getCharacter(){
        return character;
    }

    public String getEncoding(){
        return encoding;
    }
}
