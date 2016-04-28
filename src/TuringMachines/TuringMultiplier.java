package TuringMachines;

/**
 * This class is a UI for a multiplication on the Universal Turing Machine
 */
public class TuringMultiplier {
    private final static String TRANSITIONS = "010101010011010010100100110100010001000010110001010001010110001001000100101100010001000010001001100001010000010001001100001001000000000000100010011000001010000010100110000010010000001001001100000010000100000001000010110000001010000000010000010011000000010100000001010110000000100100000001001011000000010001000000000000100010011000000001010000000010100110000000010000100000000100001001100000000100010000000001010110000000001010000000001010110000000001000010000000001000010110000000001000001000000000010000010011000000000010100000000100000100110000000000100001000000000001000010110000000000010000010000000000010101100000000000100100010010110000000000001010000000000001000100110000000000001001000000000000100010011000000000000100001001000100";

    public static void main(String[] args) {
        String[] factors = new String[2];
        try {
            if (args.length > 0) {
                factors = args[0].split("\\*", 2);
            } else {
                throw new IllegalArgumentException("A multiplication in the format '13*6' is needed as the parameter!");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        TuringMachine machine = new TuringMachine(
                TRANSITIONS + "111" + Unary.encodeNumber(Integer.parseInt(factors[0]))
                        + 1 + Unary.encodeNumber(Integer.parseInt(factors[1])));
        machine.run();

        System.out.println("Let me convert this for you stupid humans, the result is: \n");
        System.out.println(machine.getTape().countZeros());
    }
}
