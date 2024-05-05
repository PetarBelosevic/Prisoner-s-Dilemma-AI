package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;
import evolution.manager.IEvolutionManager;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 *      JPanel that shows progression in evolution process.
 * </p>
 * Panel shows the best, median and the worst score in every 10th generation.
 */
public class EvolutionLogsJPanel extends JPanel {
//    private final JButton pauseButton = new MyJButton("Pause", GUIApp.NORMAL_FONT_SIZE);
//    private final JButton stopButton = new MyJButton("Stop", GUIApp.NORMAL_FONT_SIZE);
    private final JButton nextButton = new MyJButton("Next", GUIApp.NORMAL_FONT_SIZE);

    private IEvolutionManager<?> manager;

    public EvolutionLogsJPanel() {
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
        topPanel.add(new MyJLabel("Generation index", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        topPanel.add(new MyJLabel("Best score", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        topPanel.add(new MyJLabel("Median score", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        topPanel.add(new MyJLabel("Worst score", SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        add(topPanel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel(new GridLayout(0, 4));
        // TODO
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
//        bottomPanel.add(pauseButton);
//        bottomPanel.add(stopButton);
        bottomPanel.add(nextButton);
        nextButton.setEnabled(false);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

//    public JButton getPauseButton() {
//        return pauseButton;
//    }
//
//    public JButton getStopButton() {
//        return stopButton;
//    }

    /**
     * <p>
     *     JButton for jumping to the next step.
     * </p>
     * Button is initially disabled. It becomes enabled when evolution process ends.
     *
     * @return JButton for jumping to the next step.
     */
    public JButton getNextButton() {
        return nextButton;
    }
}
