package application;

import application.panels.*;
import evolution.EvolutionGUI;
import evolution.IEvolution;
import evolution.SimpleEvolution;
import evolution.manager.IEvolutionManager;
import evolution.manager.SimpleEvolutionManager;
import evolution.specimen.SimpleNeuralNetworkSpecimen;
import evolution.specimen.evaulator.IEvaluator;
import evolution.specimen.evaulator.PDEvaluator;
import evolution.specimen.factory.ElmanNeuralNetworkFactory;
import evolution.specimen.factory.ISpecimenFactory;
import game.IGame;
import game.PDGame;
import game.PDGameGUI;
import game.player.AIPDPlayer;
import game.player.GUIPlayer;

import javax.swing.*;
import java.awt.*;

public class GUIApp extends JFrame {
    public static final float NORMAL_FONT_SIZE = 20F;
    public static final float BIG_FONT_SIZE = 30F;
    public static final float SMALL_FONT_SIZE = 10F;

    private final MainJPanel mainPanel = new MainJPanel();
    private final EvolutionSetUpJPanel evolutionSetUpPanel = new EvolutionSetUpJPanel();
    private final EvolutionLogsJPanel<SimpleNeuralNetworkSpecimen> evolutionLogsPanel = new EvolutionLogsJPanel<>();
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

    /**
     * <p>
     *     Initialization of the GUI.
     * </p>
     */
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
        evolutionSetUpPanel.getStartButton().addActionListener(e -> {
            DefaultListModel<Integer> indexList = new DefaultListModel<>();
            DefaultListModel<Integer> bestList = new DefaultListModel<>();
            DefaultListModel<Integer> medianList = new DefaultListModel<>();
            DefaultListModel<Integer> worstList = new DefaultListModel<>();

            IEvolution<SimpleNeuralNetworkSpecimen> evolution = createEvolution(indexList, bestList, medianList, worstList);
            evolution.setOneParent(evolutionSetUpPanel.getNumberOfParents() == 1);
            IEvolutionManager<SimpleNeuralNetworkSpecimen> manager = new SimpleEvolutionManager<>(
                    evolution,
                    evolutionSetUpPanel.getMaxGenerationLimit(),
                    5 * evolutionSetUpPanel.getGameIterations() * evolutionSetUpPanel.getGenerationSize() + 1
            );
            evolutionLogsPanel.setManager(manager, indexList, bestList, medianList, worstList);

            change(evolutionSetUpPanel, evolutionLogsPanel);
            evolutionLogsPanel.startEvolution();
        });

        gameSetUpPanel.getBackButton().addActionListener(e -> {
            change(gameSetUpPanel, mainPanel);
        });
        gameSetUpPanel.getStartButton().addActionListener(e -> {
            DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
            DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
            IGame<GUIPlayer, GUIPlayer> game = new PDGameGUI<>(new GUIPlayer(), new GUIPlayer(), 10, player1Scores, player2Scores);
            gameLogsPanel.setGame(game, player1Scores, player2Scores);
            change(gameSetUpPanel, gameLogsPanel);
            gameLogsPanel.startGame();
        });

//        evolutionLogsPanel.getPauseButton().addActionListener(e -> {
//            // TODO pause evolution
//            if (evolutionLogsPanel.getPauseButton().getText().equals("Pause")) {
//                evolutionLogsPanel.getPauseButton().setText("Continue");
//            }
//            else {
//                evolutionLogsPanel.getPauseButton().setText("Pause");
//            }
//        });
//        evolutionLogsPanel.getStopButton().addActionListener(e -> {
//            // TODO stop evolution
//            evolutionLogsPanel.getNextButton().setEnabled(true);
//        });
        evolutionLogsPanel.getNextButton().addActionListener(e -> {
            // TODO setup game
            DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
            DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
            IGame<GUIPlayer, AIPDPlayer> game = new PDGameGUI<>(new GUIPlayer(), new AIPDPlayer(evolutionLogsPanel.getManager().getEvolution().getBestSpecimen()), 10, player1Scores, player2Scores);
            gameLogsPanel.setGame(game, player1Scores, player2Scores);
            change(evolutionLogsPanel, gameLogsPanel);
            gameLogsPanel.startGame();
        });

        add(mainPanel);
    }

    /**
     * @return new IEvolution based on the parameters set on the input
     */
    private IEvolution<SimpleNeuralNetworkSpecimen> createEvolution(DefaultListModel<Integer> indexList, DefaultListModel<Integer> bestList, DefaultListModel<Integer> medianList, DefaultListModel<Integer> worstList) {
        ISpecimenFactory<SimpleNeuralNetworkSpecimen> factory = new ElmanNeuralNetworkFactory();
        IGame<AIPDPlayer, AIPDPlayer> game = new PDGame<>(new AIPDPlayer(), new AIPDPlayer(), evolutionSetUpPanel.getGameIterations());
        IEvaluator<SimpleNeuralNetworkSpecimen> evaluator = new PDEvaluator(game);
        return new EvolutionGUI<>(
                new SimpleEvolution<>(
                    evolutionSetUpPanel.getSmallMutationChance(),
                    evolutionSetUpPanel.getSmallMutationMagnitude(),
                    evolutionSetUpPanel.getBigMutationChance(),
                    evolutionSetUpPanel.getBigMutationMagnitude(),
                    evolutionSetUpPanel.getGenerationSize(),
                    factory,
                    evaluator
                ),
                1,
                indexList,
                bestList,
                medianList,
                worstList
        );
    }

    /**
     *  <p>
     *      Changes JPanel object that is being displayed.
     *  </p>
     * @param oldComp currently displayed panel
     * @param newComp new panel for display
     */
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
