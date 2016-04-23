import java.util.HashMap;

public class TransitionMap {
	private final static String transitionDelimiter = "11";
	private final static String charDelimiter = "1";
	private final static String mapDelimiter = ",";
	
	private HashMap<String,String> transitionMap = new HashMap<>();
	
	public TransitionMap(String input){
		this(input,false);
	}
	
	public TransitionMap(String input, boolean path){
		if(path == false){
			fillTransitionMap(input);
		}else{
			//TODO implement reading from file
		}
	}
	
	private void fillTransitionMap(String input){
		String[] transitions = input.split(transitionDelimiter);
		for(int i = 0;i < transitions.length;i++){
			String[] transitionComponents = transitions[i].split(charDelimiter);
			String key = transitionComponents[0].length() + mapDelimiter + transitionComponents[1];
			String value = transitionComponents[2].length() + mapDelimiter + transitionComponents[3]
					+ mapDelimiter + transitionComponents[4];
			transitionMap.put(key, value);
		}
	}

	public String[] getTransition(int state, char readCharacter){
		 String[] output = null;
		 String transition = transitionMap.get(state + charDelimiter + readCharacter);
		 if(transition != null){
			 output = transition.split(charDelimiter);
		 }
		 
		 return output;
	}

}
