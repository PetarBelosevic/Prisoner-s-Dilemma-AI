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
    private final JSpinner smallMutationChanceSpinner = createJSpinner(0.05, 0.0, 1.0, 0.01);
    private final JSpinner smallMutationMagnitudeSpinner = createJSpinner(1, 0, -1, -1);
    private final JSpinner bigMutationChanceSpinner = createJSpinner(0.01, 0.0, 1.0, 0.001);
    private final JSpinner bigMutationMagnitudeSpinner = createJSpinner(6, 0, -1, -1);
    private final JSpinner generationSizeSpinner = createJSpinner(40, 0, -1, -1);
    private final JSpinner maxGenerationLimitSpinner = createJSpinner(40, 0, -1, -1);
    private final JSpinner gameIterationsSpinner = createJSpinner(40, 0, -1, -1);

    public EvolutionSetUpJPanel() {
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 40, 40));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        centerPanel.add(createJLabel("small mutation chance: "));
        centerPanel.add(smallMutationChanceSpinner);

        centerPanel.add(createJLabel("small mutation magnitude: "));
        centerPanel.add(smallMutationMagnitudeSpinner);

        centerPanel.add(createJLabel("big mutation chance: "));
        centerPanel.add(bigMutationChanceSpinner);

        centerPanel.add(createJLabel("big mutation magnitude: "));
        centerPanel.add(bigMutationMagnitudeSpinner);

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
        JSpinner spinner;
        if (max == -1 && step == -1) {
            SpinnerModel model = new SpinnerNumberModel();
            model.setValue(value);
            spinner = new JSpinner(model);
            spinner.addChangeListener((e) -> {
                double v = (double) spinner.getValue();
                if (v < min) {
                    spinner.setValue(spinner.getNextValue());
                }
            });
        }
        else {
            SpinnerModel model = new SpinnerNumberModel(value, min, max, step);
            spinner = new JSpinner(model);
        }
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
    public double getSmallMutationChance() {
        return (double) smallMutationChanceSpinner.getValue();
    }

    /**
     * @return small mutation magnitude on input
     */
    public int getSmallMutationMagnitude() {
        double x = (double) smallMutationMagnitudeSpinner.getValue();
        return (int) x;
    }

    /**
     * @return big mutation chance on input
     */
    public double getBigMutationChance() {
        return (double) bigMutationChanceSpinner.getValue();
    }

    /**
     * @return big mutation magnitude on input
     */
    public int getBigMutationMagnitude() {
        double x = (double) bigMutationMagnitudeSpinner.getValue();
        return (int) x;
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
