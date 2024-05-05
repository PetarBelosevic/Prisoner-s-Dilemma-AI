package application.components;

import javax.swing.*;

public class MyJButton extends JButton {
    public MyJButton(String text, float fontSize) {
        super(text);
        this.setFont(this.getFont().deriveFont(fontSize));
    }
}
