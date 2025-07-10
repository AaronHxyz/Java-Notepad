import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/*** Main GUI class for the Notepad application.
 * Handles window creation, menu actions, undo/redo, and text editing features. */
public class GUI extends JFrame implements ActionListener {

    private JFrame window;
    private TextEditor textEditor;
    public File_Function file;
    public Format_Function format;
    public CareTaker caretaker = new CareTaker(); //memento caretaker
    private boolean isRestoring = false;
    public boolean isWordWrapOn = false;
    private final int defaultFontSize;
    private int currentFontSize;

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createWindow();

        //initialize text editor and add to window
        textEditor = new TextEditor();
        window.add(textEditor.getScrollPane());

        //initialize file and format handlers with reference to this GUI
        file = new File_Function(this);
        format = new Format_Function(this);

        //add key listener for keyboard shortcuts
        textEditor.getTextArea().addKeyListener(new KeyHandler(this));

        //creates menu and passes caretaker and textEditor for undo/redo stuff
        Menu menu = new Menu(this, caretaker, textEditor);
        window.setJMenuBar(menu.createMenuBar());

        //variables used for zooming in/out and restoring to default
        defaultFontSize = textEditor.getFontSize();
        currentFontSize = defaultFontSize;

        window.setVisible(true);

        //saves the initial text state
        caretaker.save(new TextMemento(textEditor.getTextArea().getText()));

        //listens to document changes
        textEditor.getTextArea().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saveState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //usually not fired for plain text components
            }


            /*** Saves the current text state to the undo stack if not restoring from undo/redo. */
            private void saveState() {
                if (!isRestoring) {
                    caretaker.save(new TextMemento(textEditor.getTextArea().getText()));
                }
            }
        });

        //custom close operation to confirm before exit
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitAttempt();
            }
        });
    }

    /*** Creates and configures the main JFrame window.
     * Sets size and centers the window on screen. */
    private void createWindow() {
        window = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setLocationRelativeTo(null); // Center on screen
    }


    /*** Getter for the main text area's JTextArea component.
     * @return the JTextArea inside the text editor */
    public JTextArea getTextArea() {
        return textEditor.getTextArea();
    }


    /*** Getter for the main JFrame window.
     * @return the JFrame window*/
    public JFrame getWindow() {
        return window;
    }

    /*** Sets the restoring flag to indicate when undo/redo is in progress.
     * Prevents saving state during text restoration.
     * @param flag true if restoring from undo/redo, false otherwise*/
    public void setIsRestoring(boolean flag) {
        this.isRestoring = flag;
    }

    /*** Handles menu action commands triggered by user interaction.
     * Calls appropriate file, format, or editor functions.
     * @param e the ActionEvent triggered by menu item */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // switch case function calls for every menu option
        switch (command) {
            case "New": file.newFile(); break;
            case "New Window": file.newWindow(); break;
            case "Open...": file.openFile(); break;
            case "Save": file.saveFile(); break;
            case "Save As...": file.saveFileAs(); break;
            case "Exit": exitAttempt(); break;
            case "Cut": textEditor.getTextArea().cut(); break;
            case "Copy": textEditor.getTextArea().copy(); break;
            case "Paste": textEditor.getTextArea().paste(); break;
            case "Find":
                if (!textEditor.getTextArea().getText().isEmpty()) {
                    FindDialog findDialog = new FindDialog(window, textEditor.getTextArea());
                    findDialog.setVisible(true);
                }
                break;
            case "Word Wrap":
                format.wordWrap();
                JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
                source.setSelected(isWordWrapOn);
                break;
            case "Font...": format.showFontDialog(); break;
            case "Zoom In": {
                currentFontSize += 2;
                Font currentFont = textEditor.getTextArea().getFont();
                Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), currentFontSize);
                textEditor.getTextArea().setFont(newFont);
                break;
            }
            case "Zoom Out": {
                if (currentFontSize > 8) {
                    currentFontSize -= 2;
                    Font currentFont = textEditor.getTextArea().getFont();
                    Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), currentFontSize);
                    textEditor.getTextArea().setFont(newFont);
                }
                break;
            }
            case "Restore Default Zoom": {
                currentFontSize = defaultFontSize;
                Font currentFont = textEditor.getTextArea().getFont();
                Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), currentFontSize);
                textEditor.getTextArea().setFont(newFont);
            }
        }
    }

    /*** Called when user attempts to close the window.
     * If there is unsaved text, prompts the user to save or discard changes.
     * Otherwise, disposes the window to exit. */
    private void exitAttempt() {
        if (!textEditor.getTextArea().getText().isEmpty()) {
            Exit_function dialog = new Exit_function(window, textEditor.getTextArea(), () -> {
                file.saveFile();
                window.dispose();
            });

            dialog.setVisible(true);

            if (dialog.closing()) {
                window.dispose();
            }

        } else {
            window.dispose();
        }
    }
}
