package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;
import game.IGame;

import javax.swing.*;
import java.awt.*;

public class GameLogsJPanel extends JPanel {
    private final JLabel player1TotalScore = new MyJLabel("0", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK));
    private final JLabel player2TotalScore = new MyJLabel("0", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK));

    private IGame<?, ?> game;

    public GameLogsJPanel() {
        initGui();
    }

    private void initGui() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 0));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(new MyJLabel("Player 1", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        topPanel.add(new MyJLabel("Player 2", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        add(topPanel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel(new GridLayout(0, 4));
        // TODO
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 0));
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.add(player1TotalScore);
        bottomPanel.add(player2TotalScore);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void showEndMessage() {
        JOptionPane.showMessageDialog(
                this,
                "Score:" +
                        "   Player 1: " +
                        "   Player 2: ",
                "Game over",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
