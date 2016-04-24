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

public class TuringMachine {
	private static final String inputDelimiter = "111";
    private static final String blank = "2";
	private TransitionMap transitions;
	private String tape;
	private int tapeHeadPosition;
	private int currentState;
	private boolean machineIsStuck;
	private boolean verboseMode;
	
	private TuringMachine(String startConfiguration, boolean verboseMode){
		tapeHeadPosition = 0;
		currentState = 0;
		machineIsStuck = false;
        this.verboseMode = verboseMode;
		
		tape = startConfiguration.split(inputDelimiter,2)[1];
		transitions = new TransitionMap(startConfiguration.split(inputDelimiter,2)[0]);
	}

    private TuringMachine (String startConfiguration){
        this(startConfiguration,true);
    }
	
	private TuringMachine(){
		this("01001001001001101010101001100101001010011001001001001001100100010001000100111000001",true);
	}
	
	public static void main(String[] args){
		if(args.length == 2){
			new TuringMachine(args[0],Boolean.valueOf(args[1])).run();
		}else if (args.length == 1) {
            new TuringMachine(args[0]).run();
        } else {
			new TuringMachine().run();
		}
	}

    /**
     * Main program which simulates the computations the TM makes
     */
	private void run(){
		while(!machineIsStuck) {
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
                checkForAcceptedState();
            }
        }
	}

    /**
     * checks if the machine got stuck in a accepted state and prints the result to the console
     */
    private void checkForAcceptedState() {
        if (currentState == 2) {
            System.out.println("Accepted word");
        } else {
            System.out.println("Your word is shit");
        }
    }

    /**
     * Prints the current tape content and the current state of the TM to the Console
     * The Output is limited to 15 chars before and after the current position of the tape head
     * Blanks are printed too
     */
    private void printWorkingState() {
        System.out.println(tape.substring(tapeHeadPosition-16,tapeHeadPosition-1) + "|q" + currentState + "|"
        + tape.substring(tapeHeadPosition,tapeHeadPosition+16));
    }

    /**
     * Fills the tape with blanks to create the necessary length for the verbose output
     */
    private void createMinimalTapeLength() {
        while(tapeHeadPosition <= 15){
            tape = blank + tape;
            tapeHeadPosition++;
        }
        while (tape.substring(tapeHeadPosition,tape.length()).length() <= 15 ){
            tape = tape + blank;
        }
    }

    /**
     * modifies the content of the tape with the new character that should be written at the current
     * tape head position
     * @param newChar the char that will be written to the tape at the current tape head position
     */
	private void writeModifiedTapeContent(String newChar) {
		String prefix = "";
		String suffix = "";
		
		if(tapeHeadPosition > 0){
			prefix = tape.substring(0,tapeHeadPosition);
		}
		if(tapeHeadPosition < tape.length()-1){
			suffix = tape.substring(tapeHeadPosition+1,tape.length());
		}
		
		tape = prefix + newChar + suffix;
	}

    /**
     * moves the tape head one character in the given direction
     * @param direction the direction in human-readable format ("L" or "R")
     */
	private void moveTapeHead(String direction) {
		if("R".equals(direction)) {
			tapeHeadPosition++;
		}else{
			tapeHeadPosition--;
		}
	}
}
