package TuringMachines;

import java.util.HashMap;

/**
 * This class is used to map the input from the TuringMachines.TuringMachine, consisting of a current state
 * and a input character to the action which the TM should execute next (so called transition)
 * A transition map entry has the following format:
 * key: current state, character read from the tape
 * value: new state, character to be written, direction to move the tape head
 *
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 */

class TransitionMap {
    private final String TRANSITION_DELIMITER = TuringMachine.getTransitionDelimiter();
    private final String CHAR_DELIMITER = TuringMachine.getCharDelimiter();
    private final String MAP_DELIMITER = ",";

    private final HashMap<String, String> transitionMap;

    TransitionMap(String input, boolean path) {
        transitionMap = new HashMap<>();
        if (!path) {
            fillTransitionMap(input);
        } else {
            //TODO implement reading from file
        }
    }

    TransitionMap(String input) {
        this(input, false);
    }

    /**
     * Initializes the map with the transitions the TM received as part of its input
     *
     * @param transitionString the encoded string of transitions
     */
    private void fillTransitionMap(String transitionString) {
        String[] transitions = transitionString.split(TRANSITION_DELIMITER);
        for (String transition : transitions) {
            String[] transitionComponents = transition.split(CHAR_DELIMITER);
            String key = Unary.decodeNumber(transitionComponents[0]) + MAP_DELIMITER
                    + Unary.decodeSymbol(transitionComponents[1]);
            String value = Unary.decodeNumber(transitionComponents[2]) + MAP_DELIMITER
                    + Unary.decodeSymbol(transitionComponents[3]) + MAP_DELIMITER
                    + Unary.decodeDirection(transitionComponents[4]);
            transitionMap.put(key, value);
        }
    }

    /**
     * Searches the Map for a matching transition for the current state of the TM
     *
     * @param state         current state of the TM
     * @param readCharacter character read from the tape
     * @return the transition the TM should execute or null if nothing was found
     */
    String[] getTransition(int state, char readCharacter) {
        String[] output = null;
        String transition = transitionMap.get(state + MAP_DELIMITER + readCharacter);
        if (transition != null) {
            output = transition.split(MAP_DELIMITER);
        }

        return output;
    }
}
