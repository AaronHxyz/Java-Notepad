import javax.swing.*;
import java.awt.*;

/*** Creates the menu bar and handles undo/redo logic via memento pattern.
 * This class constructs the "File", "Edit", and "Format" menus with appropriate items,
 * sets fonts, and attaches action listeners to handle user interactions. */
public class Menu {

    private JMenuBar menuBar;
    private final Font menuFont = new Font("Consolas", Font.PLAIN, 11);
    private GUI gui;
    private CareTaker caretaker;
    private TextEditor textEditor;

    public Menu(GUI gui, CareTaker caretaker, TextEditor textEditor) {
        this.gui = gui;
        this.caretaker = caretaker;
        this.textEditor = textEditor;
    }

    /*** Creates and returns the menu bar containing File, Edit, and Format menus.
     * @return the JMenuBar with all menus and items configured */
    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();

        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createFormatMenu());

        return menuBar;
    }

    /*** Creates the file menu.
     * @return JMenu representing the File menu*/
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(menuFont);

        fileMenu.add(createMenuItem("New"));
        fileMenu.add(createMenuItem("New Window"));
        fileMenu.add(createMenuItem("Open..."));
        fileMenu.add(createMenuItem("Save"));
        fileMenu.add(createMenuItem("Save As..."));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Exit"));

        return fileMenu;
    }

    /*** Creates the edit menu.
     * Undo and Redo interact with the caretaker to restore previous text states.
     * @return JMenu representing the Edit menu */
    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(menuFont);

        //undo menu item with action listener that restores previous text state
        JMenuItem undo = new JMenuItem("Undo");
        undo.setFont(menuFont);
        undo.addActionListener(e -> {
            TextMemento prev = caretaker.undo(textEditor.getTextArea().getText());
            if (prev != null) {
                gui.setIsRestoring(true); //prevent save during undo
                textEditor.getTextArea().setText(prev.getText());
                gui.setIsRestoring(false);
            }
        });
        editMenu.add(undo);

        //redo menu item with action listener that restores next text state
        JMenuItem redo = new JMenuItem("Redo");
        redo.setFont(menuFont);
        redo.addActionListener(e -> {
            TextMemento next = caretaker.redo(textEditor.getTextArea().getText());
            if (next != null) {
                gui.setIsRestoring(true); //prevent save during redo
                textEditor.getTextArea().setText(next.getText());
                gui.setIsRestoring(false);
            }
        });
        editMenu.add(redo);

        editMenu.addSeparator();
        editMenu.add(createMenuItem("Cut"));
        editMenu.add(createMenuItem("Copy"));
        editMenu.add(createMenuItem("Paste"));
        editMenu.addSeparator();
        editMenu.add(createMenuItem("Find"));

        return editMenu;
    }

    /*** Creates the format menu.
     * Word Wrap is a JCheckBoxMenuItem that toggles word wrap.
     * Font submenu allows choosing different fonts.
     * Font Size submenu allows selecting font sizes.
     * @return JMenu representing the Format menu */
    private JMenu createFormatMenu() {
        JMenu formatMenu = new JMenu("Format");
        formatMenu.setFont(menuFont);

        //word wrap item
        JCheckBoxMenuItem wordWrapItem = new JCheckBoxMenuItem("Word Wrap");
        wordWrapItem.setFont(menuFont);
        wordWrapItem.setActionCommand("Word Wrap");
        wordWrapItem.addActionListener(gui);
        formatMenu.add(wordWrapItem);

        JMenuItem fontDialogItem = new JMenuItem("Font...");
        fontDialogItem.setFont(menuFont);
        fontDialogItem.setActionCommand("Font...");
        fontDialogItem.addActionListener(gui);

        formatMenu.add(fontDialogItem);

        return formatMenu;
    }

    /*** Utility method to create a JMenuItem with a given label,
     * sets the font, action command, and attaches the GUI as the action listener.
     * @param label the text for the menu item
     * @return configured JMenuItem*/
    private JMenuItem createMenuItem(String label) {
        JMenuItem item = new JMenuItem(label);
        item.setFont(menuFont);
        item.setActionCommand(label);
        item.addActionListener(gui); //pass GUI as listener for file commands
        return item;
    }
}
