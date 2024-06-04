package application.components;

import game.player.GUIPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * <p>
 *     Action for making binary decisions for non-AI GUI player.
 * </p>
 * Action specifies accelerator key and player's move.
 */
public class PlayerAction extends AbstractAction {
    private final GUIPlayer player;
    private final boolean cooperateButton;

    public PlayerAction(GUIPlayer player, String accelerator, boolean cooperate) {
        this.player = player;
        this.cooperateButton = cooperate;
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cooperateButton) {
            player.setCooperateFlag(true);
        }
        else {
            player.setDefectFlag(true);
        }
    }
}
