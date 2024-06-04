package application.panels;

import application.GUIApp;
import application.components.MyJLabel;
import application.components.MyJList;
import application.components.PlayerAction;
import game.IGame;
import game.PDGame;
import game.observers.GameObserverAdapter;
import game.player.GUIPlayer;
import utils.Pair;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 *     Panel that shows results of each round of the Prisoner's Dilemma game and the total score of both players.
 * </p>
 */
public class GameLogsJPanel extends JPanel {
    private final JLabel player1TotalScore = new MyJLabel("0", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK));
    private final JLabel player2TotalScore = new MyJLabel("0", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK));
    private final JPanel centerPanel = new JPanel(new GridLayout(0, 3));

    private IGame<?, ?> game;

    public GameLogsJPanel() {
        initGui();
    }

    /**
     * <p>
     *     Initialization of GUI.
     * </p>
     */
    private void initGui() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 0));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(new MyJLabel("Game round", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
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

    /**
     * <p>
     *     Shows endgame results.
     * </p>
     * @param player1Score player1's score
     * @param player2Score player2's score
     */
    private void showEndMessage(int player1Score, int player2Score) {
        String winner;
        if (player1Score > player2Score) {
            winner = "Player 1 wins!";
        }
        else if (player2Score > player1Score) {
            winner = "Player 2 wins!";
        }
        else {
            winner = "Draw!";
        }

        String message = String.format(
                "Score:\n\tPlayer 1: %d\n\tPlayer 2: %d\nFinal result: %s",
                player1Score, player2Score, winner
        );

        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                this,
                message,
                "Game over",
                JOptionPane.INFORMATION_MESSAGE
        ));
    }

    /**
     * <p>
     * Sets up GUI game.
     * </p>
     * Method adds proper JLists to the central panel and sets up controls for non-AI players.
     *
     * @param game game engine
     * @param player1Scores list model of player 1's previous scores
     * @param player2Scores list model of player 2's previous scores
     * @param gameRound list of game round indexes
     */
    public void setGame(PDGame<?, ?> game, DefaultListModel<Integer> player1Scores, DefaultListModel<Integer> player2Scores, DefaultListModel<Integer> gameRound) {
        this.game = game;
        centerPanel.removeAll();
        centerPanel.add(new MyJList<>(
                gameRound,
                SwingConstants.CENTER,
                GUIApp.NORMAL_FONT_SIZE,
                BorderFactory.createLineBorder(Color.BLACK)
        ));
        centerPanel.add(new MyJList<>(
                player1Scores,
                SwingConstants.CENTER,
                GUIApp.NORMAL_FONT_SIZE,
                BorderFactory.createLineBorder(Color.BLACK)
        ));
        centerPanel.add(new MyJList<>(
                player2Scores,
                SwingConstants.CENTER,
                GUIApp.NORMAL_FONT_SIZE,
                BorderFactory.createLineBorder(Color.BLACK)
        ));
        game.addGameObserver(new GameObserverAdapter() {
            int totalPlayer1 = Integer.parseInt(player1TotalScore.getText());
            int totalPlayer2 = Integer.parseInt(player2TotalScore.getText());

            @Override
            public void scoresAdded(Pair<Integer, Integer> scores) {
                totalPlayer1 += scores.getFirst();
                totalPlayer2 += scores.getSecond();
                player1TotalScore.setText(String.valueOf(totalPlayer1));
                player2TotalScore.setText(String.valueOf(totalPlayer2));
            }
        });

        if (game.getPlayer1() instanceof GUIPlayer) {
            PlayerAction player1Cooperate = new PlayerAction((GUIPlayer) game.getPlayer1(), "W", true);
            PlayerAction player1Defect = new PlayerAction((GUIPlayer) game.getPlayer1(), "S", false);

            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player1Cooperate.getValue(Action.ACCELERATOR_KEY), "player1 cooperate");
            getActionMap().put("player1 cooperate", player1Cooperate);
            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player1Defect.getValue(Action.ACCELERATOR_KEY), "player1 defect");
            getActionMap().put("player1 defect", player1Defect);
        }
        if (game.getPlayer2() instanceof GUIPlayer) {
            PlayerAction player2Cooperate = new PlayerAction((GUIPlayer) game.getPlayer2(), "P", true);
            PlayerAction player2Defect = new PlayerAction((GUIPlayer) game.getPlayer2(), "L", false);

            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player2Cooperate.getValue(Action.ACCELERATOR_KEY), "player2 cooperate");
            getActionMap().put("player2 cooperate", player2Cooperate);
            getInputMap(WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) player2Defect.getValue(Action.ACCELERATOR_KEY), "player2 defect");
            getActionMap().put("player2 defect", player2Defect);
        }
    }

    /**
     * @return game object used to simulate game
     */
    public IGame<?, ?> getGame() {
        return game;
    }

    /**
     * <p>
     *     Starts game engine in new thread.
     * </p>
     * @return thread in which game is started.
     */
    public Thread startGame() {
        Thread t = new Thread(() -> {
           game.run();
           showEndMessage(game.getPlayer1().getScore(), game.getPlayer2().getScore());
        });
        t.start();
        return t;
    }
}
