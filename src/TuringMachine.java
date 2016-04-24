
/**
 * This class represents a universal turing machine (TM) interpreter
 *
 * It accepts a unary encoded TM as input including states and the word it should compute
 * If set to verbose Mode the machine will print the current state and the position of the tape head
 * for every computation step it makes to the console.
 *
 * Possible ways to start the machine:
 * - No input: the machine will run the hardcoded example program in verbose mode
 * - A encoded TM: the machine will execute the TM it was given in verbose mode
 * - A encoded TM and verboseMode param: the machine will run completely according to the input
 *
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 */

class TuringMachine {
    final static String TRANSITION_DELIMITER = "11";
    final static String CHAR_DELIMITER = "1";
    static final String BLANK = "_";
    private static final String INPUT_DELIMITER = "111";
    private static final int ACCEPTED_STATE = 2;

    private final TransitionMap transitions;
    private final boolean verboseMode;
    private String tape;
    private int tapeHeadPosition;
    private int currentState;
    private int computationSteps;
    private boolean machineIsStuck;

    TuringMachine(String startConfiguration, boolean verboseMode) {
        tapeHeadPosition = 0;
        currentState = 1;
        machineIsStuck = false;
        this.verboseMode = verboseMode;
        computationSteps = 0;

        tape = startConfiguration.split(INPUT_DELIMITER, 2)[1];
        transitions = new TransitionMap(startConfiguration.split(INPUT_DELIMITER, 2)[0]);
    }

    TuringMachine(String startConfiguration) {
        this(startConfiguration, true);
    }

    private TuringMachine() {
        this("01001001001001101010101001100101001010011001001001001001100100010001000101110000010", true);
    }

    /**
     * Main program which simulates the computations the TM makes
     */
    void run() {
        while (!machineIsStuck) {
            computationSteps++;
            createMinimalTapeLength();

            if (verboseMode) {
                printWorkingState();
            }

            char currentChar = tape.charAt(tapeHeadPosition);

            String[] transition = transitions.getTransition(currentState, currentChar);
            if (transition != null) {
                currentState = Integer.parseInt(transition[0]);

                writeModifiedTapeContent(transition[1]);

                moveTapeHead(transition[2]);
            } else {
                machineIsStuck = true;
                printSummary();
            }
        }
    }

    /**
     * Prints the current tape content and the current state of the TM to the Console
     * The Output is limited to 15 chars before and after the current position of the tape head
     * Blanks are printed too
     */
    private void printWorkingState() {
        System.out.println(tape.substring(tapeHeadPosition - 15, tapeHeadPosition) + "|q" + currentState + "|"
                + tape.substring(tapeHeadPosition, tapeHeadPosition + 16));
    }

    /**
     * Fills the tape with blanks to create the necessary length for the verbose output
     */
    private void createMinimalTapeLength() {
        while (tapeHeadPosition < 15) {
            tape = BLANK + tape;
            tapeHeadPosition++;
        }
        while (tape.substring(tapeHeadPosition, tape.length()).length() <= 15) {
            tape = tape + BLANK;
        }
    }

    /**
     * modifies the content of the tape with the new character that should be written at the current
     * tape head position
     *
     * @param newChar the char that will be written to the tape at the current tape head position
     */
    private void writeModifiedTapeContent(String newChar) {
        String prefix = "";
        String suffix = "";

        if (tapeHeadPosition > 0) {
            prefix = tape.substring(0, tapeHeadPosition);
        }
        if (tapeHeadPosition < tape.length() - 1) {
            suffix = tape.substring(tapeHeadPosition + 1, tape.length());
        }

        tape = prefix + newChar + suffix;
    }

    /**
     * moves the tape head one character in the given direction
     *
     * @param direction the direction in human-readable format ("L" or "R")
     */
    private void moveTapeHead(String direction) {
        if ("R".equals(direction)) {
            tapeHeadPosition++;
        } else if ("L".equals(direction)) {
            tapeHeadPosition--;
        } else {
            throw new IllegalArgumentException("Invalid direction");
        }
    }

    /**
     * prints a final summary of the computation to the console
     */
    private void printSummary() {
        System.out.println("\n\n---Computation completed---\n");

        System.out.println("Current state, tape content and tape head position:");
        printWorkingState();

        if (currentState == ACCEPTED_STATE) {
            System.out.println("This is an accepting state!");
        } else {
            System.out.println("This state is not accepting!");
        }

        System.out.println("Steps needed to complete the computation:\n" + computationSteps);
    }
}