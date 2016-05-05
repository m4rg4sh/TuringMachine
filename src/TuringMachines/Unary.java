package TuringMachines;

/**
 * This class en- and decodes unary numbers, symbols and directions for the universal turing machine
 *
 * @author Lawrence Markwalder <markwlaw@students.zhaw.ch>
 * @author Luca Egli <eglilu01@students.zhaw.ch>
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 */
class Unary {
    private static final String BLANK = TuringMachine.getBlank();

    /**
     * encodes a decimal number in unary encoding with 0s
     *
     * @param number the number to encode
     * @return number in unary encoding
     */
    static String encodeNumber(int number) {
        String unaryNumber = "";
        for (int i = 0; i < number; i++) {
            unaryNumber += "0";
        }
        return unaryNumber;
    }

    /**
     * decodes a number from unary encoding with 0s to decimal
     *
     * @param unaryNumber number in unary encoding
     * @return the number in decimal
     */
    static int decodeNumber(String unaryNumber) {
        int number = 0;
        for (char character : unaryNumber.toCharArray()) {
            if ("0".equals(character + "")) {
                number++;
            }
        }
        return number;
    }

    /**
     * decodes a TM symbol from unary
     *
     * @param unarySymbol the symbol in unary encoding
     * @return the symbol in a human-readable format
     */
    static String decodeSymbol(String unarySymbol) {
        String symbol;
        if (unarySymbol.equals("000")) {
            symbol = BLANK;
        } else {
            symbol = "" + (decodeNumber(unarySymbol) - 1);
        }
        return symbol;
    }

    /**
     * decodes the directions "L" and "R" from unary
     * @param unaryNumber the direction in unary encoding
     * @return the direction in a human-readable format
     */
    static String decodeDirection(String unaryNumber) {
        if (unaryNumber.equals("0")) {
            return "L";
        } else if (unaryNumber.equals("00")) {
            return "R";
        } else {
            throw new IllegalArgumentException("Invalid Direction");
        }
    }
}
