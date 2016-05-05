package TuringMachines;

/**
 * This class represents a universal turing machine (TM) interpreter written for the THIN module @ ZHAW
 *
 * It accepts a unary encoded TM as input including states and the word it should compute
 * If the verbose Mode is activated the machine will print the current state and the position of the tape head
 * for every computational step it makes to the console.
 *
 * Possible ways to start the machine:
 * - A encoded TM: the machine will execute the TM it was given in verbose mode
 * - A encoded TM and verboseMode param: the machine will run completely according to the input
 *
 * @author Lawrence Markwalder <markwlaw@students.zhaw.ch>
 * @author Luca Egli <eglilu01@students.zhaw.ch>
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 */

class TuringMachine {
    private static final String INPUT_DELIMITER = "111";
    private static final String TRANSITION_DELIMITER = "11";
    private static final String CHAR_DELIMITER = "1";
    private static final String BLANK = "_";
    private static final int ACCEPTED_STATE = 2;
    private static final int OUTPUT_SIZE = 15;

    private final TransitionMap transitions;
    private final boolean verboseMode;
    private Tape tape;
    private int currentState;
    private int computationSteps;
    private boolean machineIsStuck;


    TuringMachine(String inputString, boolean verboseMode) {
        currentState = 1;
        computationSteps = 0;
        machineIsStuck = false;
        this.verboseMode = verboseMode;

        transitions = new TransitionMap(inputString.split(INPUT_DELIMITER, 2)[0]);
        tape = new Tape(inputString.split(INPUT_DELIMITER, 2)[1]);
        printIntroduction(inputString);
    }

    TuringMachine(String inputString) {
        this(inputString, true);
    }

    /**
     * @return the delimiter between two transitions
     */
    static String getTransitionDelimiter() {
        return TRANSITION_DELIMITER;
    }

    /**
     * @return the delimiter used between two characters
     */
    static String getCharDelimiter() {
        return CHAR_DELIMITER;
    }

    /**
     * @return the character used as the blank char
     */
    static String getBlank() {
        return BLANK;
    }

    /**
     * Main program which simulates the computations the TM makes
     */
    void run() {
        System.out.println("\nWorking...");
        while (!machineIsStuck) {
            computationSteps++;
            tape.createMinimalTapeLength();

            if (verboseMode) {
                printWorkingState();
            }

            String[] transition = transitions.getTransition(currentState, tape.getCurrentChar());
            if (transition != null) {
                currentState = Integer.parseInt(transition[0]);
                tape.writeModifiedTapeContent(transition[1]);
                tape.moveTapeHead(transition[2]);
            } else {
                machineIsStuck = true;
                printSummary();
            }
        }
    }

    /**
     * prints the introduction to the computation to the console
     */
    private void printIntroduction(String inputString) {
        System.out.println("-----------------------------------------");
        System.out.println("Hi I'm a universal turing machine");
        System.out.println("\nThe encoded machine I'm gonna emulate looks like this:");
        System.out.println(inputString + "\n");
        System.out.print("Alright, lets do this!");
    }

    /**
     * prints a final summary of the computation to the console
     */
    private void printSummary() {
        System.out.println("\n---Computation completed---\n");

        System.out.println("Current state, tape content and tape head position:");
        printWorkingState();

        if (currentState == ACCEPTED_STATE) {
            System.out.println("This is an accepting state!");
        } else {
            System.out.println("This state is not accepting!");
        }

        System.out.println("Steps needed to complete the computation:\n" + computationSteps);
    }

    /**
     * prints the current tape content, tape head position and TM state to the console
     */
    private void printWorkingState() {
        System.out.println(tape.getTapeContentBeforeHead(OUTPUT_SIZE) + "|q" + currentState + "|" + tape.getCurrentChar()
                + tape.getTapeContentAfterHead(OUTPUT_SIZE));
    }

    /**
     * @return the tape object this TM uses
     */
    Tape getTape() {
        return tape;
    }
}