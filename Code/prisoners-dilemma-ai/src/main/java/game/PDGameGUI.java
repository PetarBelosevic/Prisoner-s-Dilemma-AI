package game;

import game.player.GUIPlayer;
import game.player.IPlayer;

import javax.swing.*;

public class PDGameGUI<T extends IPlayer, D extends IPlayer> extends PDGame<T, D> {
    private final DefaultListModel<Integer> player1Scores;
    private final DefaultListModel<Integer> player2Scores;
    private final DefaultListModel<Integer> gameRound = new DefaultListModel<>();
    private int index = 1;

    public PDGameGUI(T player1, D player2, int iterations, DefaultListModel<Integer> player1Scores, DefaultListModel<Integer> player2Scores) {
        super(player1, player2, iterations);
        this.player1Scores = player1Scores;
        this.player2Scores = player2Scores;
    }

    @Override
    protected void evaluateDecisions() {
        if (getPlayer1() instanceof GUIPlayer) {
            ((GUIPlayer) getPlayer1()).setCooperateFlag(false);
            ((GUIPlayer) getPlayer1()).setDeflectFlag(false);
        }
        if (getPlayer2() instanceof GUIPlayer) {
            ((GUIPlayer) getPlayer2()).setCooperateFlag(false);
            ((GUIPlayer) getPlayer2()).setDeflectFlag(false);
        }
        super.evaluateDecisions();
        player1Scores.addElement(getPlayer1().getScoreHistory().get(getPlayer1().getScoreHistory().size() - 1));
        player2Scores.addElement(getPlayer2().getScoreHistory().get(getPlayer2().getScoreHistory().size() - 1));
        gameRound.addElement(index++);
    }

    public DefaultListModel<Integer> getPlayer1Scores() {
        return player1Scores;
    }

    public DefaultListModel<Integer> getPlayer2Scores() {
        return player2Scores;
    }

    public DefaultListModel<Integer> getGameRound() {
        return gameRound;
    }
}
