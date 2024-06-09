package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 *     JPanel for configuration of parameters for evolution of artificial neural networks.
 * </p>
 * Parameters for configuration are:
 * small mutation chance, small mutation magnitude, big mutation chance, big mutation magnitude,
 * generation size, max generation limit and number of game iterations.
 */
public class EvolutionSetUpJPanel extends JPanel {
    private final JButton backButton = new MyJButton("Back", GUIApp.NORMAL_FONT_SIZE);
    private final JButton startButton = new MyJButton("Start", GUIApp.NORMAL_FONT_SIZE);
    private final JSpinner mutationChanceSpinner = createJSpinner(0.05, 0.0, 1.0, 0.01);
    private final JSpinner mutationMagnitudeSpinner = createJSpinner(0.5, 0.0, -1, 0.1);
    private final JSpinner generationSizeSpinner = createJSpinner(50, 0, -1, -1);
    private final JSpinner maxGenerationLimitSpinner = createJSpinner(30,   0, -1, -1);
    private final JSpinner gameIterationsSpinner = createJSpinner(50, 0, -1, -1);

    public EvolutionSetUpJPanel() {
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 40, 40));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        centerPanel.add(createJLabel("mutation chance: "));
        centerPanel.add(mutationChanceSpinner);

        centerPanel.add(createJLabel("mutation magnitude: "));
        centerPanel.add(mutationMagnitudeSpinner);

        centerPanel.add(createJLabel("generation size: "));
        centerPanel.add(generationSizeSpinner);

        centerPanel.add(createJLabel("max generation limit: "));
        centerPanel.add(maxGenerationLimitSpinner);

        centerPanel.add(createJLabel("game iterations: "));
        centerPanel.add(gameIterationsSpinner);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.add(backButton);
        bottomPanel.add(startButton);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    /**
     * <p>
     *     Creates new JSpinner based on the given parameters.
     * </p>
     * If max == -1 && step == -1 then max is considered to be infinite, and step is considered to be 1.
     *
     * @param value initial value
     * @param min minimum value
     * @param max maximum value
     * @param step step for the next value
     * @return new JSpinner
     */
    private JSpinner createJSpinner(double value, double min, double max, double step) {
        if (max == -1) {
            max = Double.MAX_VALUE;
        }
        if (step == -1) {
            step = 1;
        }
        SpinnerModel model = new SpinnerNumberModel(value, min, max, step);
        JSpinner spinner = new JSpinner(model);
        spinner.setFont(spinner.getFont().deriveFont(GUIApp.NORMAL_FONT_SIZE));
        return  spinner;
    }

    /**
     * @param text content of the JLabel
     * @return new JLabel wit trailing alignment
     */
    private JLabel createJLabel(String text) {
        JLabel label = new MyJLabel(text, GUIApp.NORMAL_FONT_SIZE);
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        return label;
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

    /**
     * @return small mutation chance on input
     */
    public double getMutationChance() {
        return (double) mutationChanceSpinner.getValue();
    }

    /**
     * @return small mutation magnitude on input
     */
    public double getMutationMagnitude() {
        return (double) mutationMagnitudeSpinner.getValue();
    }

    /**
     * @return size of one generation on input
     */
    public int getGenerationSize() {
        double x = (double) generationSizeSpinner.getValue();
        return (int) x;
    }

    /**
     * @return max generation limit on input
     */
    public int getMaxGenerationLimit() {
        double x = (double) maxGenerationLimitSpinner.getValue();
        return (int) x;
    }

    /**
     * @return game iterations on input
     */
    public int getGameIterations() {
        double x = (double) gameIterationsSpinner.getValue();
        return (int) x;
    }
}
