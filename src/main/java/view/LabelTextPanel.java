package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
        this.setBackground(Color.getHSBColor(0.9F, 0F, 0.05F));
        label.setForeground(Color.getHSBColor(0.9F, 0.2F, 1F));
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
    }
}
