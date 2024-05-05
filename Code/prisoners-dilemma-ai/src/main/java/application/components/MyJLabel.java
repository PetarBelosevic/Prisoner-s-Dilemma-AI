package application.components;

import javax.swing.*;
import javax.swing.border.Border;

public class MyJLabel extends JLabel {
    public MyJLabel(String text, float fontSize) {
        this(text, SwingConstants.LEADING, fontSize);
    }

    public MyJLabel(String text, float fontSize, Border border) {
        this(text, SwingConstants.LEADING, fontSize, border);
    }

    public MyJLabel(String text, int horizontalAlignment, float fontSize) {
        super(text, horizontalAlignment);
        this.setFont(this.getFont().deriveFont(fontSize));
    }

    public MyJLabel(String text, int horizontalAlignment, float fontSize, Border border) {
        this(text, horizontalAlignment, fontSize);
        this.setBorder(border);
    }
}
