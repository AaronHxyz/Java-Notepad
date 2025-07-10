import javax.swing.*;
import java.awt.*;

/*** Encapsulates a text editing component consisting of a JTextArea
 * wrapped inside a JScrollPane with scrollbars as needed.
 * This class provides getter methods to access the text area and scroll pane. */
public class TextEditor {

    private JTextArea textArea;
    private JScrollPane scrollPane;

    /*** Constructs a TextEditor with a JTextArea configured with default font and wrapped
     * inside a JScrollPane that provides vertical and horizontal scrollbars as needed.*/
    public TextEditor() {
        textArea = new JTextArea();

        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }

    /*** Returns the JScrollPane containing the text area.
     * This can be added to a container to display the text editor with scrollbars.
     * @return JScrollPane that wraps the JTextArea*/
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /*** Returns the JTextArea component where the user can input and edit text.
     * Allows direct manipulation of the text content and properties.
     * @return JTextArea used for text editing*/
    public JTextArea getTextArea() {
        return textArea;
    }

    public int getFontSize() {return textArea.getFont().getSize(); }
}

