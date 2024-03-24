package application;

import javax.swing.*;
import java.awt.*;

public class GUIApp extends JFrame {

    public GUIApp() throws HeadlessException {
        setTitle("Prisoner's Dilemma AI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(500, 20);
        setSize(800, 800);

        initGUI();
    }

    private void initGUI() {
        getContentPane().setLayout(new BorderLayout());
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIApp().setVisible(true));
    }
}
