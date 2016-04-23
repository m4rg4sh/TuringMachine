
public class TuringMachine {
	private static final String inputDelimiter = "111";
	private TransitionMap transitions;
	private String tape;
	private int tapeHeadPosition;
	private int currentState;
	private boolean machineIsStuck;
	
	public TuringMachine(String startConfiguration){
		tapeHeadPosition = 0;
		currentState = 0;
		machineIsStuck = false;
		
		tape = startConfiguration.split(inputDelimiter,2)[1];
		transitions = new TransitionMap(startConfiguration.split(inputDelimiter,2)[0]);
	}
	
	public TuringMachine(){
		this("010010010010011010101010011001010010100110010010010010011001000100010001001110011100");
	}
	
	public static void main(String[] args){
		if(args.length>0){
			new TuringMachine(args[0]).run();
		}else{
			new TuringMachine().run();
		}
	}
	
	public void run(){
		while(!machineIsStuck){
			char currentChar = tape.charAt(tapeHeadPosition);

			String[] transition = transitions.getTransition(currentState,currentChar);
			if(transition != null){
				currentState = Integer.parseInt(transition[0]);
				
				writeModifiedTapeContent(transition[1]);
				
				moveTapeHead(transition[2]);
			}else{
				machineIsStuck = true;
				if(currentState == 2){
					System.out.println("Accepted word");
				}else{
					System.out.println("Your word is shit");
				}
			}
		}
	}

	private void checkForEndOfTape() {
		if(tapeHeadPosition == (tape.length())){
			tape = tape + "2";
		}
		if(tapeHeadPosition < 0){
			tapeHeadPosition = 0;
			tape = "2" + tape;
		}
	}

	private void writeModifiedTapeContent(String newChar) {
		String prefix = "";
		String suffix = "";
		
		if(tapeHeadPosition > 0){
			prefix = tape.substring(0,tapeHeadPosition);
		}
		if(tapeHeadPosition < tape.length()-1){
			suffix = tape.substring(tapeHeadPosition+1,tape.length());
		}
		
		tape = prefix + newChar + suffix ;
	}

	private void moveTapeHead(String direction) {
		if("R".equals(direction)) {
			tapeHeadPosition++;
		}else{
			tapeHeadPosition--;
		}
		checkForEndOfTape();
	}

}
