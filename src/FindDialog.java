import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*** A dialog window that allows the user to search for text in the notepad.*/
public class FindDialog extends JDialog {

    private JTextField searchField;
    private JButton findNextButton;
    private JTextArea textArea;
    private int lastMatchIndex;

    /*** Constructs the Find dialog window.
     * @param parent the parent JFrame that owns this dialog
     * @param textArea the JTextArea to search within */
    public FindDialog(JFrame parent, JTextArea textArea) {
        super(parent, "Find", false);
        this.textArea = textArea;

        setLayout(new FlowLayout());
        setSize(400, 100);
        setLocationRelativeTo(parent);

        add(new JLabel("Find What:"));
        searchField = new JTextField(20);
        add(searchField);

        findNextButton = new JButton("Find Next");
        add(findNextButton);

        findNextButton.addActionListener(e -> findNext());

        //resets search when opened
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                lastMatchIndex = 0;
                searchField.requestFocusInWindow();
            }
        });

    }

    /*** Searches for the next occurrence of the text entered in the searchField within the textArea.
     * If found, selects and highlights the match. If not found, shows a message dialog. */
    public void findNext() {
        String searchText = searchField.getText();
        String content = textArea.getText();

        if (searchText.isEmpty()) {
            return;
        }

        //gets the index of the word, lastMatchIndex keeps track of where the last match ended
        int index = content.indexOf(searchText, lastMatchIndex);

        if (index >= 0) {
            textArea.setCaretPosition(index + searchText.length()); //sets cursor position to end of searched word
            textArea.select(index, index + searchText.length()); //start index to end index of the word
            lastMatchIndex = index + searchText.length(); //gets after the found word
        }
        else {
            JOptionPane.showMessageDialog(this, "Cannot find \"" + searchText + "\"");
            lastMatchIndex = 0;
        }
    }


}
