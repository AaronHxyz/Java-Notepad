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
        addFontSizePicker();
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

        //will display current + all available font styles
        JPanel fontStylePanel = new JPanel();
        fontStylePanel.setBounds(145, 15, 125, 160);

        int currentFontStyle = gui.getTextArea().getFont().getStyle();
        String currentFontStyleName;

        switch (currentFontStyle) {
            case Font.PLAIN:
                currentFontStyleName = "Plain";
                break;
            case Font.BOLD:
                currentFontStyleName = "Bold";
                break;
            case Font.ITALIC:
                currentFontStyleName = "Italic";
                break;
            default: // bold italic
                currentFontStyleName = "Bold Italic";
                break;
        }

        JTextField currentFontStyleField = new JTextField(currentFontStyleName);
        currentFontStyleField.setPreferredSize(new Dimension(125, 25));
        currentFontStyleField.setEditable(false);
        fontStylePanel.add(currentFontStyleField);

        JPanel ListOfFontStylePanel = new JPanel();
        ListOfFontStylePanel.setLayout(new BoxLayout(ListOfFontStylePanel, BoxLayout.Y_AXIS));
        ListOfFontStylePanel.setBackground(Color.WHITE);

        JLabel plainStyle = new JLabel("Plain");
        plainStyle.setFont(new Font("Dialog", Font.PLAIN, 12));
        plainStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(plainStyle.getText());
            }

            //helps indicate that each font in the list is clickable with highlights
            @Override
            public void mouseEntered(MouseEvent e) {
                plainStyle.setOpaque(true);
                plainStyle.setBackground(Color.BLUE);
                plainStyle.setForeground(Color.WHITE);
            }

            //removes highlights
            @Override
            public void mouseExited(MouseEvent e) {
                plainStyle.setBackground(null);
                plainStyle.setForeground(null);
            }
        });
        ListOfFontStylePanel.add(plainStyle);

        JLabel boldStyle = new JLabel("Bold");
        boldStyle.setFont(new Font("Dialog", Font.BOLD, 12));
        boldStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(boldStyle.getText());
            }

            //helps indicate that each font in the list is clickable with highlights
            @Override
            public void mouseEntered(MouseEvent e) {
                boldStyle.setOpaque(true);
                boldStyle.setBackground(Color.BLUE);
                boldStyle.setForeground(Color.WHITE);
            }

            //removes highlights
            @Override
            public void mouseExited(MouseEvent e) {
                boldStyle.setBackground(null);
                boldStyle.setForeground(null);
            }
        });
        ListOfFontStylePanel.add(boldStyle);

        JLabel italicStyle = new JLabel("Italic");
        italicStyle.setFont(new Font("Dialog", Font.ITALIC, 12));
        italicStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(italicStyle.getText());
            }

            //helps indicate that each font in the list is clickable with highlights
            @Override
            public void mouseEntered(MouseEvent e) {
                italicStyle.setOpaque(true);
                italicStyle.setBackground(Color.BLUE);
                italicStyle.setForeground(Color.WHITE);
            }

            //removes highlights
            @Override
            public void mouseExited(MouseEvent e) {
                italicStyle.setBackground(null);
                italicStyle.setForeground(null);
            }
        });
        ListOfFontStylePanel.add(italicStyle);

        JLabel boldItalicStyle = new JLabel("Bold Italic");
        boldItalicStyle.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
        boldItalicStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(boldItalicStyle.getText());
            }

            //helps indicate that each font in the list is clickable with highlights
            @Override
            public void mouseEntered(MouseEvent e) {
                boldItalicStyle.setOpaque(true);
                boldItalicStyle.setBackground(Color.BLUE);
                boldItalicStyle.setForeground(Color.WHITE);
            }

            //removes highlights
            @Override
            public void mouseExited(MouseEvent e) {
                boldItalicStyle.setBackground(null);
                boldItalicStyle.setForeground(null);
            }
        });
        ListOfFontStylePanel.add(boldItalicStyle);

        JScrollPane scrollPane = new JScrollPane(ListOfFontStylePanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));
        fontStylePanel.add(scrollPane);

        add(fontStylePanel);
    }

    private void addFontSizePicker() {
        JLabel fontSizeLabel = new JLabel("Font Size:");
        fontSizeLabel.setBounds(275, 5, 125, 10);
        add(fontSizeLabel);

        JPanel fontSizePanel = new JPanel();
        fontSizePanel.setBounds(275, 15, 125, 160);

        JTextField fontSizeField = new JTextField(Integer.toString(gui.getTextArea().getFont().getSize()));
        fontSizeField.setPreferredSize(new Dimension(125, 25));
        fontSizeField.setEditable(false);
        fontSizePanel.add(fontSizeField);

        //list of font sizes
        JPanel listOfFontSizePanel = new JPanel();
        listOfFontSizePanel.setLayout(new BoxLayout(listOfFontSizePanel, BoxLayout.Y_AXIS));
        listOfFontSizePanel.setBackground(Color.WHITE);

        //sizes go from 8 to 32
        for (int i = 8; i <= 72; i += 2) {
            JLabel fontSizeValue = new JLabel(Integer.toString(i));
            fontSizeValue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    fontSizeField.setText(fontSizeValue.getText());
                }

                //helps indicate that each font in the list is clickable with highlights
                @Override
                public void mouseEntered(MouseEvent e) {
                    fontSizeValue.setOpaque(true);
                    fontSizeValue.setBackground(Color.BLUE);
                    fontSizeValue.setForeground(Color.WHITE);
                }

                //removes highlights
                @Override
                public void mouseExited(MouseEvent e) {
                    fontSizeValue.setBackground(null);
                    fontSizeValue.setForeground(null);
                }
            });

            listOfFontSizePanel.add(fontSizeValue);
        }
        JScrollPane scrollPane = new JScrollPane(listOfFontSizePanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));
        fontSizePanel.add(scrollPane);

        add(fontSizePanel);

    }
}


