package TuringMachines;

/**
 * This class represents a tape including the head for the Turing Machine
 *
 * @author Lawrence Markwalder <markwlaw@students.zhaw.ch>
 * @author Luca Egli <eglilu01@students.zhaw.ch>
 * @author Stefan Epprecht <epprest1@students.zhaw.ch>
 *
 */
class Tape {
    private static final String BLANK = TuringMachine.getBlank();
    private String content;
    private int tapeHeadPosition;

    Tape(String content) {
        this.content = content;
        tapeHeadPosition = 0;
    }

    /**
     * Gets the current char underneath the RW-head on the tape
     *
     * @return the current char on the tape
     */
    char getCurrentChar() {
        return content.charAt(tapeHeadPosition);
    }

    /**
     * Moves the tape head one char in the given direction
     *
     * @param direction the direction in which the head should move. accepted params are "L" oder "R"
     */
    void moveTapeHead(String direction) {
        if ("R".equals(direction)) {
            tapeHeadPosition++;
        } else if ("L".equals(direction)) {
            tapeHeadPosition--;
        } else {
            throw new IllegalArgumentException("Invalid direction");
        }
    }

    /**
     * Retrieves the content of the tape before the head
     *
     * @param amountOfCharacters amount of characters that should be returned
     * @return characters on the tape
     */
    String getTapeContentBeforeHead(int amountOfCharacters) {
        return content.substring(tapeHeadPosition - amountOfCharacters, tapeHeadPosition);
    }

    /**
     * Retrieves the content of the tape after the head
     *
     * @param amountOfCharacters amount of characters that should be returned
     * @return characters on the tape
     */
    String getTapeContentAfterHead(int amountOfCharacters) {
        return content.substring(tapeHeadPosition + 1, tapeHeadPosition + amountOfCharacters + 1);
    }

    /**
     * Fills the tape with blanks to create the necessary length for the verbose output
     */
    void createMinimalTapeLength() {
        while (tapeHeadPosition < 15) {
            content = BLANK + content;
            tapeHeadPosition++;
        }
        while (content.substring(tapeHeadPosition, content.length()).length() <= 15) {
            content = content + BLANK;
        }
    }

    /**
     * modifies the content of the tape with the new character that should be written at the current
     * tape head position
     *
     * @param newChar the char that will be written to the tape at the current tape head position
     */
    void writeModifiedTapeContent(String newChar) {
        String prefix = "";
        String suffix = "";

        if (tapeHeadPosition > 0) {
            prefix = content.substring(0, tapeHeadPosition);
        }
        if (tapeHeadPosition < content.length() - 1) {
            suffix = content.substring(tapeHeadPosition + 1, content.length());
        }

        content = prefix + newChar + suffix;
    }

    /**
     * @return the amount of zeros on the tape
     */
    int countZeros() {
        int counter = 0;
        for (char character : content.toCharArray()) {
            if ("0".equals(character + "")) {
                counter++;
            }
        }
        return counter;
    }
}
