package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;

import javax.swing.*;
import java.awt.*;

public class EvolutionSetUpJPanel extends JPanel {
    private final JButton backButton = new MyJButton("Back", GUIApp.NORMAL_FONT_SIZE);
    private final JButton startButton = new MyJButton("Start", GUIApp.NORMAL_FONT_SIZE);

    public EvolutionSetUpJPanel() {
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 40, 40));
        centerPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        centerPanel.add(createJLabel("small mutation chance: "));
        centerPanel.add(createJSpinner(0.05, 0.0, 1.0, 0.01));

        centerPanel.add(createJLabel("small mutation magnitude: "));
        centerPanel.add(createJSpinner(1, 0, -1, -1));

        centerPanel.add(createJLabel("big mutation chance: "));
        centerPanel.add(createJSpinner(0.01, 0.0, 1.0, 0.01));

        centerPanel.add(createJLabel("big mutation magnitude: "));
        centerPanel.add(createJSpinner(6, 0, -1, -1));

        centerPanel.add(createJLabel("number of parents: "));
        centerPanel.add(createJSpinner(1, 1, 2, 1));

        centerPanel.add(createJLabel("generation size: "));
        centerPanel.add(createJSpinner(60, 0, -1, -1));

        centerPanel.add(createJLabel("max generation limit: "));
        centerPanel.add(createJSpinner(50, 0, -1, -1));

        centerPanel.add(createJLabel("game iterations: "));
        centerPanel.add(createJSpinner(50, 0, -1, -1));
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

}
