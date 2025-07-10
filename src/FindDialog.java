import javax.swing.*;
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
        this.textArea = textArea;
        setTitle("Find");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(380, 220);
        setLocationRelativeTo(parent);
        setModal(false);

        setLayout(null);

        addWindowComponents();

    }
    private void addWindowComponents() {
        JLabel findWhatLabel = new JLabel("Find what:");
        findWhatLabel.setBounds(10, 20, 70, 25);
        add(findWhatLabel);

        searchField = new JTextField();
        searchField.setBounds(75, 20, 180, 25);
        add(searchField);

        findNextButton = new JButton("Find Next");
        findNextButton.setBounds(260, 20, 90, 25);
        add(findNextButton);

        findNextButton.addActionListener(e -> findNext());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(260, 55, 90, 25);
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

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
