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

    /*** Creates Font objects for the supported fonts at the given size.
     * These Font objects are reused when applying fonts to the text area.
     * @param fontSize the font size in points */
    public void createFont(int fontSize) {
        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSans = new Font("Comic Sans", Font.PLAIN, fontSize);
        timesNewRoman = new Font("TimesNewRoman", Font.PLAIN, fontSize);

    }

    /*** Sets the font of the text area to the selected font.
     * If the font name is null or not recognized, no action is taken.
     * @param font the name of the font to set */
    public void setFont(String font) {
        selectedFont = font;

        if (selectedFont == null) return;

        //apply selected font to text area
        switch (selectedFont) {
            case "Arial":
                gui.getTextArea().setFont(arial);
                break;
            case "Comic_Sans":
                gui.getTextArea().setFont(comicSans);
                break;
            case "Times_New_Roman":
                gui.getTextArea().setFont(timesNewRoman);
                break;
        }
    }
}
