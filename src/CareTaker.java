import java.util.Stack;

/*** The CareTaker class manages the undo and redo stacks using the Memento design pattern.
 * It stores mementos of the text area's state so that changes can be undone and redone. */
public class CareTaker {
    private final Stack<TextMemento> undoStack = new Stack<>();
    private final Stack<TextMemento> redoStack = new Stack<>();

    /*** Saves a new text state to the undo stack and clears the redo stack.
     * @param memento the TextMemento representing the current state of the text */
    public void save(TextMemento memento) {
        undoStack.push(memento);
        redoStack.clear();
    }

    /*** Undoes the last change by moving the current state to the redo stack and returning the previous state.
     * @param currentText the current state of the text (not used in this implementation, but could be logged)
     * @return the previous TextMemento to restore, or null if no undo is available */
    public TextMemento undo(String currentText) {
        if (undoStack.size() <= 1) {
            //no earlier state to undo to
            return null;
        }
        //move current state to redo
        redoStack.push(undoStack.pop());
        return undoStack.peek();  //peek previous state to restore
    }

    /*** Redoes the last undone change by popping a state from the redo stack and pushing it to the undo stack.
     * @param currentText the current state of the text (not used in this implementation, but could be logged)
     * @return the TextMemento to restore as the redone state, or null if no redo is available */
    public TextMemento redo(String currentText) {
        if (redoStack.isEmpty()) {
            return null;
        }
        TextMemento redone = redoStack.pop();
        undoStack.push(redone);
        return redone;
    }
}
