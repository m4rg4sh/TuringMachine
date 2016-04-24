/**
 * This class en- and decodes unary numbers, symbols and directions for the universal turing machine
 *
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 */
class Unary {
    private static final String BLANK = TuringMachine.BLANK;

    static String encodeNumber(int number) {
        String unaryNumber = "";
        for (int i = 0; i < number; i++) {
            unaryNumber += "0";
        }
        return unaryNumber;
    }

    static int decodeNumber(String unaryNumber) {
        int number = 0;
        for (char character : unaryNumber.toCharArray()) {
            if ("0".equals(character + "")) {
                number++;
            }
        }
        return number;
    }

    static String decodeSymbol(String unarySymbol) {
        String symbol;
        if (unarySymbol.equals("000")) {
            symbol = BLANK;
        } else {
            symbol = "" + (decodeNumber(unarySymbol) - 1);
        }
        return symbol;
    }

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
