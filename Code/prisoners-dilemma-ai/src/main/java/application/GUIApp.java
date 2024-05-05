package application;

import application.panels.*;

import javax.swing.*;
import java.awt.*;

public class GUIApp extends JFrame {
    public static final float NORMAL_FONT_SIZE = 20F;
    public static final float BIG_FONT_SIZE = 30F;
    public static final float SMALL_FONT_SIZE = 10F;

    private final MainJPanel mainPanel = new MainJPanel();
    private final EvolutionSetUpJPanel evolutionSetUpPanel = new EvolutionSetUpJPanel();
    private final EvolutionLogsJPanel evoluutionLogsPanel = new EvolutionLogsJPanel();
    private final GameSetUpJPanel gameSetUpPanel = new GameSetUpJPanel();
    private final GameLogsJPanel gameLogsPanel = new GameLogsJPanel();

    public GUIApp() throws HeadlessException {
        setTitle("Prisoner's Dilemma AI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(500, 20);
//        setLocationRelativeTo(null);
        setSize(750, 700);

        initGUI();
    }

    private void initGUI() {
        mainPanel.getTrainAI().addActionListener(e -> {
            change(mainPanel, evolutionSetUpPanel);
        });
        mainPanel.getPlay().addActionListener((e -> {
            change(mainPanel, gameSetUpPanel);
        }));

        evolutionSetUpPanel.getBackButton().addActionListener(e -> {
            change(evolutionSetUpPanel, mainPanel);
        });

        gameSetUpPanel.getBackButton().addActionListener(e -> {
            change(gameSetUpPanel, mainPanel);
        });

        add(mainPanel);
    }

    private void change(JComponent oldComp, JComponent newComp) {
        remove(oldComp);
        add(newComp);
        validate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIApp().setVisible(true));
    }
}
