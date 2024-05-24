package application;

import application.panels.*;
import evolution.EvolutionGUI;
import evolution.EvolutionLogs;
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
import game.observers.GameObserverAdapter;
import game.player.AIPDPlayer;
import game.player.GUIPlayer;
import neuralNetwork.INeuralNetwork;
import neuralNetwork.NeuralNetwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        mainPanel.getLoadNetwork().addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load Neural Network");
            if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            SimpleNeuralNetworkSpecimen network = null;
            try {
                network = new SimpleNeuralNetworkSpecimen(fileChooser.getSelectedFile().toPath().toString());
            }
            catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, "Could not load neural network from selected file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PDGameGUI<GUIPlayer, AIPDPlayer> game = getGuiPlayerAIPDPlayerPDGameGUI(network);
            gameLogsPanel.setGame(game);
            change(mainPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
        });

        evolutionSetUpPanel.getBackButton().addActionListener(e -> {
            change(evolutionSetUpPanel, mainPanel);
        });
        evolutionSetUpPanel.getStartButton().addActionListener(e -> {
            DefaultListModel<Integer> indexList = new DefaultListModel<>();
            DefaultListModel<Integer> bestList = new DefaultListModel<>();
            DefaultListModel<Integer> medianList = new DefaultListModel<>();
            DefaultListModel<Integer> worstList = new DefaultListModel<>();

            int maxFitness = 5 * evolutionSetUpPanel.getGameIterations() * evolutionSetUpPanel.getGenerationSize();
            IEvolution<SimpleNeuralNetworkSpecimen> evolution = createEvolution(indexList, bestList, medianList, worstList);
            evolution.setOneParent(evolutionSetUpPanel.getNumberOfParents() == 1);
            IEvolutionManager<SimpleNeuralNetworkSpecimen> manager = new SimpleEvolutionManager<>(
                    evolution,
                    evolutionSetUpPanel.getMaxGenerationLimit(),
                    maxFitness + 1
            );
            evolutionLogsPanel.setManager(manager, indexList, bestList, medianList, worstList, maxFitness);

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
            game.addGameObserver(new GameObserverAdapter() {
                @Override
                public void gameStopped() {
                    player1.setStopFlag(true);
                }
            });
            game.addGameObserver(new GameObserverAdapter() {
                @Override
                public void gameStopped() {
                    player2.setStopFlag(true);
                }
            });
            gameLogsPanel.setGame(game);
            change(gameSetUpPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
        });

        evolutionLogsPanel.getNextButton().addActionListener(e -> {
            PDGameGUI<GUIPlayer, AIPDPlayer> game = getGuiPlayerAIPDPlayerPDGameGUI(evolutionLogsPanel.getManager().getEvolution().getBestSpecimen());
            gameLogsPanel.setGame(game);
            change(evolutionLogsPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
        });

        add(mainPanel);
    }

    /**
     * <p>
     *     Prepares PDGame for oe GUI player and one AI player.
     * </p>
     * @param network for AI player
     * @return prepared game
     */
    private static PDGameGUI<GUIPlayer, AIPDPlayer> getGuiPlayerAIPDPlayerPDGameGUI(SimpleNeuralNetworkSpecimen network) {
        DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
        DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
        GUIPlayer player = new GUIPlayer();
        PDGameGUI<GUIPlayer, AIPDPlayer> game = new PDGameGUI<>(player, new AIPDPlayer(network), 10, player1Scores, player2Scores);
        game.addGameObserver(new GameObserverAdapter() {
            @Override
            public void gameStopped() {
                player.setStopFlag(true);
            }
        });
        return game;
    }

    /**
     * @return new IEvolution based on the parameters set on the input
     */
    private IEvolution<SimpleNeuralNetworkSpecimen> createEvolution(DefaultListModel<Integer> indexList, DefaultListModel<Integer> bestList, DefaultListModel<Integer> medianList, DefaultListModel<Integer> worstList) {
        ISpecimenFactory<SimpleNeuralNetworkSpecimen> factory = new ElmanNeuralNetworkFactory();
        IGame<AIPDPlayer, AIPDPlayer> game = new PDGame<>(new AIPDPlayer(), new AIPDPlayer(), evolutionSetUpPanel.getGameIterations());
        IEvaluator<SimpleNeuralNetworkSpecimen> evaluator = new PDEvaluator(game);
        return new EvolutionGUI<>(
                new EvolutionLogs<>(
                        new SimpleEvolution<>(
                                evolutionSetUpPanel.getSmallMutationChance(),
                                evolutionSetUpPanel.getSmallMutationMagnitude(),
                                evolutionSetUpPanel.getBigMutationChance(),
                                evolutionSetUpPanel.getBigMutationMagnitude(),
                                evolutionSetUpPanel.getGenerationSize(),
                                factory,
                                evaluator
                        ),
                        5
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
