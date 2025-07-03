/*** The Memento class that stores a snapshot of the text content.
 * Used to implement undo/redo functionality by saving and restoring text states. */
public class TextMemento {
    //stores the text snapshot
    private final String text;

    /*** Constructs a TextMemento with the given text snapshot.
     * @param text The text to be saved as a memento.*/
    public TextMemento(String text) {
        this.text = text;
    }

    /*** Returns the saved text snapshot stored in this memento.
     * @return The text stored in this memento. */
    public String getText() {
        return text;
    }
}
