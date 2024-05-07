package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;
import application.components.MyJList;
import application.components.PlayerAction;
import game.IGame;
import game.player.GUIPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameLogsJPanel extends JPanel {
    private final JLabel player1TotalScore = new MyJLabel("0", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK));
    private final JLabel player2TotalScore = new MyJLabel("0", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK));
    private final JPanel centerPanel = new JPanel(new GridLayout(0, 2));

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

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setColumnHeaderView(topPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 0));
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.add(player1TotalScore);
        bottomPanel.add(player2TotalScore);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void showEndMessage(int player1Score, int player2Score) {
        JOptionPane.showMessageDialog(
                this,
                "Score:" +
                        "\n   Player 1: " + player1Score +
                        "\n   Player 2: " + player2Score,
                "Game over",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void setGame(IGame<?, ?> game, DefaultListModel<Integer> player1Scores, DefaultListModel<Integer> player2Scores) {
        this.game = game;
        centerPanel.removeAll();
        centerPanel.add(new MyJList<>(player1Scores, SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        centerPanel.add(new MyJList<>(player2Scores, SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        // TODO da nije hardcoded

        if (game.getPlayer1() instanceof GUIPlayer) {
            PlayerAction player1Cooperate = new PlayerAction((GUIPlayer) game.getPlayer1(), "W", true);
            PlayerAction player1Deflect = new PlayerAction((GUIPlayer) game.getPlayer1(), "S", false);

            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player1Cooperate.getValue(Action.ACCELERATOR_KEY), "player1 cooperate");
            getActionMap().put("player1 cooperate", player1Cooperate);
            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player1Deflect.getValue(Action.ACCELERATOR_KEY), "player1 deflect");
            getActionMap().put("player1 deflect", player1Deflect);
        }
        if (game.getPlayer2() instanceof GUIPlayer) {
            PlayerAction player2Cooperate = new PlayerAction((GUIPlayer) game.getPlayer2(), "P", true);
            PlayerAction player2Deflect = new PlayerAction((GUIPlayer) game.getPlayer2(), "L", false);

            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player2Cooperate.getValue(Action.ACCELERATOR_KEY), "player2 cooperate");
            getActionMap().put("player2 cooperate", player2Cooperate);
            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player2Deflect.getValue(Action.ACCELERATOR_KEY), "player2 deflect");
            getActionMap().put("player2 deflect", player2Deflect);
        }
    }

    public void startGame() {
        Thread t = new Thread(() -> {
           game.run();
           showEndMessage(game.getPlayer1().getScore(), game.getPlayer2().getScore());
        });
        t.start();
    }
}
