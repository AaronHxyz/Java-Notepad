import javax.swing.*;
import java.awt.*;

/*** makes a custom window that prompts the user to save changes
 * when attempting to close the notepad. */
public class Exit_function extends JDialog {

    private boolean closing = false; //indicates whether the application should proceed with closing
    private JButton saveButton, dontSaveButton, cancelButton;

    /*** Makes the Exit_function dialog.
     * @param parent the parent JFrame (main application window)
     * @param textArea the JTextArea whose content may need to be saved (passed for context)
     * @param onSaveAndExit a Runnable to execute the save logic before closing */
    public Exit_function(JFrame parent, JTextArea textArea, Runnable onSaveAndExit) {
        super(parent, "Notepad", true);

        setSize(350, 120);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel message = new JLabel("Do you want to save changes?");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        saveButton = new JButton("Save");
        dontSaveButton = new JButton("Don't Save");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(saveButton);
        buttonPanel.add(dontSaveButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.CENTER);

        //save button: run save logic, mark as closing, then close dialog
        saveButton.addActionListener(e -> {
            onSaveAndExit.run();
            closing = true;
            dispose();
        });

        //don't Save button: skip saving, mark as closing, then close dialog
        dontSaveButton.addActionListener(e -> {
            closing = true;
            dispose();
        });

        //cancel button: do not close, just close dialog without updating `closing`
        cancelButton.addActionListener(e -> {
            closing = false;
            dispose();
        });
    }

    /*** Indicates whether the application should proceed with closing after dialog interaction.
     * @return true if user clicked Save or Don't Save, false if Cancel */
    public boolean closing() {
        return closing;
    }
}
