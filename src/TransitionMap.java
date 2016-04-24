import java.util.HashMap;

/**
 * This class is used to map the input from the TuringMachine, consisting of a current state
 * and a input character to the action which the TM should execute next (so called transition)
 * A transition map entry has the following format:
 * key: current state, character read from the tape
 * value: new state, character to be written, direction to move the tape head
 *
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 */

class TransitionMap {
	private final static String transitionDelimiter = "11";
	private final static String charDelimiter = "1";
	private final static String mapDelimiter = ",";
	
	private HashMap<String,String> transitionMap;
	
	TransitionMap(String input, boolean path){
		transitionMap = new HashMap<>();
		if(!path){
			fillTransitionMap(input);
		}else{
			//TODO implement reading from file
		}
	}

    TransitionMap(String input){
        this(input,false);
    }

    /**
     * Initializes the map with the transitions the TM received as part of its input
     * @param transitionString the encoded string of transitions
     */
	private void fillTransitionMap(String transitionString){
		String[] transitions = transitionString.split(transitionDelimiter);
        for (String transition : transitions) {
            String[] transitionComponents = transition.split(charDelimiter);
            String key = (transitionComponents[0].length() - 1) + mapDelimiter + (transitionComponents[1].length() - 1);
            String value = (transitionComponents[2].length() - 1) + mapDelimiter + (transitionComponents[3].length() - 1)
                    + mapDelimiter + getDirection(transitionComponents[4]);
            transitionMap.put(key, value);
        }
	}

    /**
     * Searches the Map for a matching transition for the current state of the TM
     * @param state current state of the TM
     * @param readCharacter character read from the tape
     * @return the transition the TM should execute or null if nothing was found
     */
	String[] getTransition(int state, char readCharacter){
		 String[] output = null;
		 String transition = transitionMap.get(state + mapDelimiter + readCharacter);
		 if(transition != null){
			 output = transition.split(mapDelimiter);
		 }
		 
		 return output;
	}

    /**
     * Decodes the direction for the movement of the tape head from unary to a more readable format
     * @param encodedDirection the direction encoded in unary
     * @return the direction in a human-readable format
     */
    private String getDirection(String encodedDirection){
        if(encodedDirection.equals("0")) {
            return "L";
        } else {
            return "R";
        }
    }

}
