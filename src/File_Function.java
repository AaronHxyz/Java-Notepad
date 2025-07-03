import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


/*** Handles all file operations under the File menu in the Notepad app */
public class File_Function {

    GUI gui;
    String filename;
    String path;

    public File_Function(GUI gui) {
        this.gui = gui;
    }


    /*** Creates a new blank file. If the current text area has content,
     * prompts the user to save before clearing the text area. */
    public void newFile(){

        String currentText = gui.getTextArea().getText();

        //checks for text, if there is ask user if they want to overwrite/save file
        if (!currentText.isEmpty()) {
            int option = JOptionPane.showConfirmDialog(
                    gui.getWindow(),
                    "Do you want to save changes to your current file?",
                    "Notepad",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (option == JOptionPane.YES_OPTION) {
                saveFile();
                gui.getTextArea().setText("");
                gui.getWindow().setTitle("New Window");
            }
            else if (option == JOptionPane.NO_OPTION) {
                gui.getTextArea().setText("");
                gui.getWindow().setTitle("New Window");
            }

            else {
                gui.getTextArea().setText("");
                gui.getWindow().setTitle("New Window");
            }
        }
    }

    /*** Opens a completely new window of the Notepad application. */
    public void newWindow(){
        new GUI();
    }

    /*** Prompts the user to select a file and loads its contents into the text area. */
    public void openFile(){

        FileDialog fd = new FileDialog(gui.getWindow(), "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            filename = fd.getFile();
            path = fd.getDirectory();
            gui.getWindow().setTitle(filename);
        }

        //actually opens the file reads each line and displays it
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + filename));
            gui.getTextArea().setText("");

            String line;
            while ((line = br.readLine()) != null) {
                gui.getTextArea().append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File could not be opened" + e);
        }
    }

    /*** Saves the current content to the existing file. If the file has not been named yet,
     * triggers a Save As dialog. */
    public void saveFile(){

        if (filename == null) {
            saveFileAs();
        }
        else {
            try {
                FileWriter fw = new FileWriter(path + filename);
                fw.write(gui.getTextArea().getText());
                fw.close();

            } catch (Exception e) {
                System.out.println("File could not be saved" + e);
            }
        }

    }

    /*** Opens a Save dialog to save the current content to a new file. */
    public void saveFileAs(){

        FileDialog fd = new FileDialog(gui.getWindow(), "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            filename = fd.getFile();
            path = fd.getDirectory();
            gui.getWindow().setTitle(filename);
        }

        try {
            FileWriter fw = new FileWriter(path + filename);
            fw.write(gui.getTextArea().getText());
            fw.close();

        } catch (Exception e) {
            System.out.println("File could not be saved" + e);
        }

    }
}
