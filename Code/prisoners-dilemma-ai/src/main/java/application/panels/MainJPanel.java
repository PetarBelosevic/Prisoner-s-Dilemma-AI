package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 *     JPanel for starting view of application.
 * </p>
 * Panel contains button for starting AI training process, starting two-player game,
 * loading existing neural network and additional information button.
 */
public class MainJPanel extends JPanel {
    private final JLabel title = new MyJLabel("Prisoner's Dilemma", 2 * GUIApp.BIG_FONT_SIZE);
    private final JButton trainAI = new MyJButton("Train AI", GUIApp.NORMAL_FONT_SIZE);
    private final JButton play = new MyJButton("2 players", GUIApp.NORMAL_FONT_SIZE);
    private final JButton info = new MyJButton("?", GUIApp.NORMAL_FONT_SIZE);
    private final MyJButton loadNetwork = new MyJButton("Load Neural Network", GUIApp.NORMAL_FONT_SIZE);
    private final GridBagConstraints constraints = new GridBagConstraints();

    public MainJPanel() {
        info.addActionListener((e) -> JOptionPane.showMessageDialog(
                this,
                """
                        This application lets you play Prisoner's Dilemma.
                        You can play this game in two modes:
                           1. Against AI player
                                In this case you can train your neural network using genetic algorithm. In this case
                                AI player will use trained network against you.
                                Other option is to load existing neural network from a file which will be used by the
                                AI player.
                           2. Against other player
                        Enjoy the game!""",
                "About the application",
                JOptionPane.INFORMATION_MESSAGE
        ));

        setBackground(Color.LIGHT_GRAY);

        title.setFont(title.getFont().deriveFont(40.0F));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        addBlank(0, 0);
        addBlank(0, 1); addElement(title, 1, 1, 4); addBlank(5, 1);
        addBlank(0, 2);

        addBlank(0, 3);
        addBlank(0, 4); addElement(trainAI, 1, 4, 4); addBlank(5, 4);
        addBlank(0, 5);

        addBlank(0, 6); addElement(play, 1, 6, 4); addBlank(5, 6);
        addBlank(0, 7);

        addBlank(0, 8); addElement(loadNetwork, 1, 8, 4); addBlank(5, 8);
        addBlank(0, 9);

        addBlank(0, 10);
        constraints.anchor = GridBagConstraints.SOUTHEAST;
        constraints.fill = GridBagConstraints.NONE;
        addElement(info, 4, 11, 1);

        addBlank(0, 10);
    }

    /**
     * <p>
     *     Adds JComponent to this panel.
     * </p>
     * @param element item to be added
     * @param x position of the element at x-axis
     * @param y position of the element at y-axis
     * @param yPad padding of the element over y-axis
     * @param width number of columns that element will occupy
     */
    private void addComponent(JComponent element, int x, int y, int yPad, int width) {
        constraints.ipady = yPad;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        add(element, constraints);
    }

    /**
     * <p>
     *     Adds non-blank element to this panel.
     * </p>
     * @param element item to be added
     * @param x position of the element at x-axis
     * @param y position of the element at y-axis
     * @param width number of columns that element will occupy
     */
    private void addElement(JComponent element, int x, int y, int width) {
        addComponent(element, x, y, 10, width);
    }

    /**
     * <p>
     *     Adds blank JLabel to this panel.
     * </p>
     * @param x position of the element at x-axis
     * @param y position of the element at y-axis
     */
    private void addBlank(int x, int y) {
        JLabel l = new JLabel("");
        addComponent(l, x, y, 40, 1);
    }

    /**
     * @return JButton for training AI
     */
    public JButton getTrainAI() {
        return trainAI;
    }

    /**
     * @return JButton for playing the game.
     */
    public JButton getPlay() {
        return play;
    }

    /**
     * @return JButton for loading existing neural network
     */
    public JButton getLoadNetwork() {
        return loadNetwork;
    }
}
