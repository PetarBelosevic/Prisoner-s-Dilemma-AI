package application.components;

import javax.swing.*;

public class MyJLabel extends JLabel {
    public MyJLabel(String text, float fontSize) {
        super(text);
        this.setFont(this.getFont().deriveFont(fontSize));
    }

    public MyJLabel(String text, int horizontalAlignment, float fontSize) {
        super(text, horizontalAlignment);
        this.setFont(this.getFont().deriveFont(fontSize));
    }
}
