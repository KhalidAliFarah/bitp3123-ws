package wordCounterInServerSide;



/**
 * 
 * @author: Khalid
 * 
 * The GenerateText class represents a text generator. It holds a text string
 * and provides methods to retrieve and modify the text.
 */
public class GenerateText {
    private String text = "My name is Khalid Ali Farah  ";

    /**
     * Retrieves the current text.
     *
     * @return The current text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets a new text.
     *
     * @param text The new text to be set.
     */
    public void setText(String text) {
        this.text = text;
    }
}
