import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FontDialog extends JDialog {

    private GUI gui;

    public FontDialog(GUI gui) {
        this.gui = gui;
        setTitle("Font Settings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(425, 350);
        setLocationRelativeTo(gui.getWindow());
        setModal(true);

        setLayout(null);

        addMenuComponents();
    }

    private void addMenuComponents() {
        addFontPicker();
        addFontStylePicker();
    }
    private void addFontPicker() {
        JLabel fontLabel = new JLabel("Font:");
        fontLabel.setBounds(10, 5, 125, 10);
        add(fontLabel);

        //current font and list of fonts to choose from
        JPanel fontPanel = new JPanel();
        fontPanel.setBounds(10, 15, 125, 160);

        JTextField currentFontField = new JTextField(gui.getTextArea().getFont().getFontName());
        currentFontField.setPreferredSize(new Dimension(125, 25));
        currentFontField.setEditable(false);
        fontPanel.add(currentFontField);

        //shows the list of available fonts
        JPanel ListOfFontsPanel = new JPanel();
        ListOfFontsPanel.setLayout(new BoxLayout(ListOfFontsPanel, BoxLayout.Y_AXIS));

        //makes the list of fonts scrollable
        JScrollPane scrollPane = new JScrollPane(ListOfFontsPanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));


        //gets all possible fonts
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        //makes a JLabel out of each available font and changes current font field to font the user clicked
        for(String fontName : fontNames) {
            JLabel label = new JLabel(fontName);

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    currentFontField.setText(fontName);
                }

                //helps indicate that each font in the list is clickable with highlights
                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setOpaque(true);
                    label.setBackground(Color.BLUE);
                    label.setForeground(Color.WHITE);
                }

                //removes highlights
                @Override
                public void mouseExited(MouseEvent e) {
                    label.setBackground(null);
                    label.setForeground(null);
                }
            });

            ListOfFontsPanel.add(label);
        }
        fontPanel.add(scrollPane);

        add(fontPanel);
    }

    private void addFontStylePicker() {
        JLabel fontStyleLabel = new JLabel("Font Style:");
        fontStyleLabel.setBounds(145, 5, 125, 10);
        add(fontStyleLabel);
    }
}


