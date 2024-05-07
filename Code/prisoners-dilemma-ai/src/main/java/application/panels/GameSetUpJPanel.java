package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;

import javax.swing.*;
import java.awt.*;

public class GameSetUpJPanel extends JPanel {
    private final JLabel player1Label = new MyJLabel("Player 1", SwingConstants.CENTER, GUIApp.BIG_FONT_SIZE);
    private final JLabel player1CoopLabel = new MyJLabel("cooperate key: W", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE);
    private final JLabel player1DeflectLabel = new MyJLabel("deflect key: S", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE);

    private final JLabel player2Label = new MyJLabel("Player 2", SwingConstants.CENTER, GUIApp.BIG_FONT_SIZE);
    private final JLabel player2CoopLabel = new MyJLabel("cooperate key: P", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE);
    private final JLabel player2DeflectLabel = new MyJLabel("deflect key: L", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE);

    private final JButton backButton = new MyJButton("Back", GUIApp.NORMAL_FONT_SIZE);
    private final JButton startButton = new MyJButton("Start", GUIApp.NORMAL_FONT_SIZE);

    public GameSetUpJPanel() {
//        initializeLabels();
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(0, 2));
        centerPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 1, Color.BLACK));
        constraints.ipady = 40;
        constraints.gridy = 0;
        leftPanel.add(player1Label, constraints);
        constraints.gridy++;
        leftPanel.add(new JLabel(""), constraints);
        constraints.gridy++;
        leftPanel.add(new JLabel(""), constraints);
        constraints.gridy++;
        leftPanel.add(player1CoopLabel, constraints);
        constraints.gridy++;
        leftPanel.add(new JLabel(""), constraints);
        constraints.gridy++;
        leftPanel.add(player1DeflectLabel, constraints);
        centerPanel.add(leftPanel);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 0, Color.BLACK));
        constraints.gridy = 0;
        rightPanel.add(player2Label, constraints);
        constraints.gridy++;
        rightPanel.add(new JLabel(""), constraints);
        constraints.gridy++;
        rightPanel.add(new JLabel(""), constraints);
        constraints.gridy++;
        rightPanel.add(player2CoopLabel, constraints);
        constraints.gridy++;
        rightPanel.add(new JLabel(""), constraints);
        constraints.gridy++;
        rightPanel.add(player2DeflectLabel, constraints);
        centerPanel.add(rightPanel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.add(backButton);
        bottomPanel.add(startButton);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    /**
     * @return JButton for returning to the previous screen
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * @return JButton starting the simulation.
     */
    public JButton getStartButton() {
        return startButton;
    }
}
