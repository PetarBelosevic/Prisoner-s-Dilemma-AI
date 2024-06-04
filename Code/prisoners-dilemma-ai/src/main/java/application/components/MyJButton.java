package application.components;

import javax.swing.*;

/**
 * <p>
 *     Extension of JButton that can specify font size in the constructor.
 * </p>
 */
public class MyJButton extends JButton {
    public MyJButton(String text, float fontSize) {
        super(text);
        this.setFont(this.getFont().deriveFont(fontSize));
    }
}
