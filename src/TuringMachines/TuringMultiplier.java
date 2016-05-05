package TuringMachines;

/**
 * This class is a UI for a multiplication on the Universal Turing Machine
 *
 * @author Lawrence Markwalder <markwlaw@students.zhaw.ch>
 * @author Luca Egli <eglilu01@students.zhaw.ch>
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 */
public class TuringMultiplier {
    private final static String TRANSITIONS = "010101010011010010100100110100010001000010110001010001010110001001000100"
            + "10110001000100001000100110000101000001000100110000100100000000000010001001100000101000001010011000001001"
            + "00000010010011000000100001000000010000101100000010100000000100000100110000000101000000010101100000001001"
            + "0000000100101100000001000100000000000010001001100000000101000000001010011000000001000010000000010000100"
            + "1100000000100010000000001010110000000001010000000001010110000000001000010000000001000010110000000001"
            + "00000100000000001000001001100000000001010000000010000010011000000000010000100000000000100001011"
            + "000000000001000001000000000001010110000000000010010001001011000000000000101000000000000100010011"
            + "0000000000001001000000000000100010011000000000000100001001000100";

    /**
     * Creates a multiplier TM with the necessary params and prints the result to the console
     *
     * @param args factor1*factor2 verboseMode
     */
    public static void main(String[] args) {
        String[] factors = new String[2];
        boolean verboseMode = false;
        try {
            if (args.length > 0) {
                factors = args[0].split("\\*", 2);
            } else {
                throw new IllegalArgumentException("A multiplication in the format '13*6' is needed as the parameter!");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }

        if (args.length >= 2) {
            verboseMode = Boolean.parseBoolean(args[1]);
        }

        TuringMachine machine = new TuringMachine(
                TRANSITIONS + "111" + Unary.encodeNumber(Integer.parseInt(factors[0]))
                        + 1 + Unary.encodeNumber(Integer.parseInt(factors[1])), verboseMode);
        machine.run();

        System.out.println("Let me convert this for you, the result is:");
        System.out.println(machine.getTape().countZeros());
    }
}
