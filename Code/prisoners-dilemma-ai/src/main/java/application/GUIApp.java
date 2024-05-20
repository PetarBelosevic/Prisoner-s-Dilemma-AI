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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     Main class for GUI application.
 * </p>
 * Application offers training artificial neural network for playing Prisoner's Dilemma and playing that game with
 * trained network or with other player.
 */
public class GUIApp extends JFrame {
    public static final float NORMAL_FONT_SIZE = 20F;
    public static final float BIG_FONT_SIZE = 30F;
    public static final float SMALL_FONT_SIZE = 10F;

    private final MainJPanel mainPanel = new MainJPanel();
    private final EvolutionSetUpJPanel evolutionSetUpPanel = new EvolutionSetUpJPanel();
    private final EvolutionLogsJPanel<SimpleNeuralNetworkSpecimen> evolutionLogsPanel = new EvolutionLogsJPanel<>();
    private final GameSetUpJPanel gameSetUpPanel = new GameSetUpJPanel();
    private final GameLogsJPanel gameLogsPanel = new GameLogsJPanel();
    private Thread evolutionThread;
    private Thread gameThread;

    public GUIApp() throws HeadlessException {
        setTitle("Prisoner's Dilemma AI");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (evolutionThread != null) {
                        evolutionLogsPanel.getManager().stopEvolution();
                        evolutionThread.join();
                    }
                    if (gameThread != null) {
                        gameLogsPanel.getGame().stopGame();
                        gameThread.join();
                    }
                }
                catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        setLocation(500, 20);
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
            evolutionThread = evolutionLogsPanel.startEvolution();
        });

        gameSetUpPanel.getBackButton().addActionListener(e -> {
            change(gameSetUpPanel, mainPanel);
        });
        gameSetUpPanel.getStartButton().addActionListener(e -> {
            DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
            DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
            GUIPlayer player1 = new GUIPlayer();
            GUIPlayer player2 = new GUIPlayer();
            PDGameGUI<GUIPlayer, GUIPlayer> game = new PDGameGUI<>(player1, player2, 10, player1Scores, player2Scores);
            game.addGameObserver(() -> player1.setStopFlag(true));
            game.addGameObserver(() -> player2.setStopFlag(true));
            gameLogsPanel.setGame(game);
            change(gameSetUpPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
        });

        evolutionLogsPanel.getNextButton().addActionListener(e -> {
            // TODO setup game
            DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
            DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
            GUIPlayer player = new GUIPlayer();
            PDGameGUI<GUIPlayer, AIPDPlayer> game = new PDGameGUI<>(player, new AIPDPlayer(evolutionLogsPanel.getManager().getEvolution().getBestSpecimen()), 10, player1Scores, player2Scores);
            game.addGameObserver(() -> player.setStopFlag(true));
            gameLogsPanel.setGame(game);
            change(evolutionLogsPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
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
