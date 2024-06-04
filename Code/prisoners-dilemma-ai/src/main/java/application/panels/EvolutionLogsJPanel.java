package application.panels;

import application.GUIApp;
import application.components.MyJButton;
import application.components.MyJLabel;
import application.components.MyJList;
import evolution.manager.IEvolutionManager;
import evolution.specimen.ISpecimen;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * <p>
 *      JPanel that shows progression in evolution process.
 * </p>
 * Panel shows the best, median and the worst score in every 10th generation.
 */
public class EvolutionLogsJPanel<T extends ISpecimen<T>> extends JPanel {
    private final JButton nextButton = new MyJButton("Next", GUIApp.NORMAL_FONT_SIZE);
    private final JButton stopButton = new MyJButton("Stop", GUIApp.NORMAL_FONT_SIZE);
    private final JLabel maxFitnessLabel = new MyJLabel("Maximum possible fitness: ", GUIApp.NORMAL_FONT_SIZE);
    private IEvolutionManager<T> manager;
    private final JPanel centerPanel = new JPanel(new GridLayout(0, 4));

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

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setColumnHeaderView(topPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.add(maxFitnessLabel);
        bottomPanel.add(stopButton);
        bottomPanel.add(nextButton);
        nextButton.setEnabled(false);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

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

    /**
     * <p>
     *     JButton for stopping evolution.
     * </p>
     * It gets disabled when evolution ends or it's stopped.
     *
     * @return JButton for stopping evolution
     */
    public JButton getStopButton() {
        return stopButton;
    }

    /**
     * <p>
     *     Sets evolution manager, table that will show logs from evolution and label that shows maximal possible score.
     * </p>
     * @param manager new evolution manager
     * @param indexList JList for showing index of generation
     * @param bestList JList for showing the best score in generation
     * @param medianList JList for showing median score in generation
     * @param worstList JList for showing the worst score in generation
     */
    public void setManager(IEvolutionManager<T> manager, DefaultListModel<Integer> indexList, DefaultListModel<Double> bestList, DefaultListModel<Double> medianList, DefaultListModel<Double> worstList) {
        this.manager = manager;
        centerPanel.removeAll();
        centerPanel.add(new MyJList<>(indexList, SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        centerPanel.add(new MyJList<>(bestList, SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        centerPanel.add(new MyJList<>(medianList, SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        centerPanel.add(new MyJList<>(worstList, SwingConstants.CENTER, GUIApp.NORMAL_FONT_SIZE, BorderFactory.createLineBorder(Color.BLACK)));
        maxFitnessLabel.setText(maxFitnessLabel.getText() + String.format(Locale.GERMAN, "%.2f", manager.getAcceptableFitness()));
    }

    /**
     * @return used evolution manager
     */
    public IEvolutionManager<T> getManager() {
        return manager;
    }

    /**
     * <p>
     *     Starts evolution.
     * </p>
     * After evolution is over, method enables next button.
     */
    public Thread startEvolution() {
        Thread t = new Thread(() -> {
            manager.runEvolution();
            stopButton.setEnabled(false);
            nextButton.setEnabled(true);
        });
        t.start();
        return t;
    }
}
