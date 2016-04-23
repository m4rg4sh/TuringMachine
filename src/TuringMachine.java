
public class TuringMachine {
	private static final String inputDelimiter = "111";
	private static final String charDelimiter = "1";
	private TransitionMap transitions;
	private String tape;
	private int tapeHeadPosition;
	private int currentState;
	private boolean machineIsStuck;
	
	public TuringMachine(String startConfiguration){
		tapeHeadPosition = 0;
		currentState = 0;
		machineIsStuck = false;
		
		tape = startConfiguration.split(inputDelimiter)[1];
		transitions = new TransitionMap(startConfiguration.split(inputDelimiter)[0]);
	}
	
	public TuringMachine(){
		this("0,1,1,1,R;0,0,0,0,R;1,0,1,0,R;1,1,1,1,R;1,_,2,_,R:01000");
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
				currentState = Integer.parseInt(""+transition[0]);
				
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
			tape = tape + "_";
		}
		if(tapeHeadPosition < 0){
			tapeHeadPosition = 0;
			tape = "_" + tape;
		}
	}

	private void writeModifiedTapeContent(char newChar) {
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

	private void moveTapeHead(char direction) {
		if("R".equals("" + direction)) {
			tapeHeadPosition++;
		}else{
			tapeHeadPosition--;
		}
		checkForEndOfTape();
	}

}
