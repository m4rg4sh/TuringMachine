import java.util.HashMap;

public class TransitionMap {
	private final static String transitionDelimiter = "11";
	private final static String charDelimiter = "1";
	private final static String mapDelimiter = ",";
	
	private HashMap<String,String> transitionMap;

	public TransitionMap(String input){
		this(input,false);
	}
	
	public TransitionMap(String input, boolean path){
		transitionMap = new HashMap<>();
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
			String key = (transitionComponents[0].length()-1) + mapDelimiter + (transitionComponents[1].length()-1);
			String value = (transitionComponents[2].length()-1) + mapDelimiter + (transitionComponents[3].length()-1)
					+ mapDelimiter + getDirection(transitionComponents[4]);
			transitionMap.put(key, value);
		}
	}

	public String[] getTransition(int state, char readCharacter){
		 String[] output = null;
		 String transition = transitionMap.get(state + mapDelimiter + readCharacter);
		 if(transition != null){
			 output = transition.split(mapDelimiter);
		 }
		 
		 return output;
	}

    private String getDirection(String input){
        if(input.equals("0")) {
            return "L";
        } else {
            return "R";
        }
    }

}
