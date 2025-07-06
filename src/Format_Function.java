import java.awt.*;

/*** Handles formatting functionality for the Notepad app,
 * including toggling word wrap and changing the font of the text area. */
public class Format_Function {

    GUI gui;
    Font arial, comicSans, timesNewRoman;
    String selectedFont;

    public Format_Function(GUI gui) {

        this.gui = gui;
    }

    /*** Toggles word wrap in the main text area. */
    public void wordWrap() {

        //enables word wrapping
        if (!gui.isWordWrapOn) {
            gui.isWordWrapOn = true;
            gui.getTextArea().setLineWrap(true);
            gui.getTextArea().setWrapStyleWord(true);
        }

        //disables word wrapping
        else {
            gui.isWordWrapOn = false;
            gui.getTextArea().setLineWrap(false);
            gui.getTextArea().setWrapStyleWord(false);
        }
    }

    public void showFontDialog() {
        FontDialog fontDialog = new FontDialog(gui);
        fontDialog.setVisible(true);
    }
}
