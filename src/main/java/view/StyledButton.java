package view;

import javax.swing.*;
import java.awt.*;

/**
 * A button stylized to this program.
 */
class StyledButton extends JButton {
    StyledButton(String text) {
        // Source (StackOverflow):
        // https://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set text
        this.setText(text);

        // Pink colour
        this.setBackground(Color.getHSBColor(0.9F, 0.2F, 1F));
        // Black text
        this.setForeground(Color.getHSBColor(0.9F, 0F, 0.05F));
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        this.setOpaque(true);
        this.setPreferredSize(new Dimension(150,25));

        this.addActionListener(e -> {
            this.setBackground(Color.getHSBColor(0.9F, 0.9F, 1F));
        });

    }
}
