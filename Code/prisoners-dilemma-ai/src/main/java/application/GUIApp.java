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
import evolution.specimen.evaulator.PDEvaluatorNetVsNet;
import evolution.specimen.evaulator.PDEvaluatorNetVsStrat;
import evolution.specimen.factory.ElmanNeuralNetworkFactory;
import evolution.specimen.factory.ISpecimenFactory;
import game.IGame;
import game.PDGame;
import game.observers.IGameObserver;
import game.player.AIPDPlayer;
import game.player.GUIPlayer;
import game.player.strategies.AbstractStrategyPlayer;
import game.player.strategies.RandomPlayer;
import utils.Constants;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;

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
                catch (InterruptedException ignored) {}
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
        initMainPanel();
        initEvolutionSetUpPanel();
        initGameSetUpPanel();
        initEvolutionLogsPanel();
        add(mainPanel);
    }

    /**
     * <p>
     *     Initialization of MainPanel.
     * </p>
     */
    private void initMainPanel() {
        mainPanel.getTrainAI().addActionListener(e -> change(mainPanel, evolutionSetUpPanel));
        mainPanel.getPlay().addActionListener((e -> change(mainPanel, gameSetUpPanel)));
        mainPanel.getLoadNetwork().addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(String.valueOf(Constants.DEFAULT_STORAGE.resolve(Path.of("selected"))));
            fileChooser.setDialogTitle("Load Neural Network");
            if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            SimpleNeuralNetworkSpecimen network;
            try {
                network = new SimpleNeuralNetworkSpecimen(Constants.DEFAULT_STORAGE.resolve(fileChooser.getSelectedFile().toPath()));
            }
            catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, "Could not load neural network from selected file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
            DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
            DefaultListModel<Integer> gameRound = new DefaultListModel<>();
            PDGame<GUIPlayer, AIPDPlayer> game = getGUIPlayerAIPDPlayerPDGame(network, player1Scores, player2Scores, gameRound);
            gameLogsPanel.setGame(game, player1Scores, player2Scores, gameRound);
            change(mainPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
        });
    }

    /**
     * <p>
     *     Initialization of EvolutionSetUpPanel.
     * </p>
     */
    private void initEvolutionSetUpPanel() {
        evolutionSetUpPanel.getBackButton().addActionListener(e -> change(evolutionSetUpPanel, mainPanel));
        evolutionSetUpPanel.getStartButton().addActionListener(e -> {
            DefaultListModel<Integer> indexList = new DefaultListModel<>();
            DefaultListModel<Double> bestList = new DefaultListModel<>();
            DefaultListModel<Double> medianList = new DefaultListModel<>();
            DefaultListModel<Double> worstList = new DefaultListModel<>();

            IEvolution<SimpleNeuralNetworkSpecimen> evolution = createEvolution(indexList, bestList, medianList, worstList);
            IEvolutionManager<SimpleNeuralNetworkSpecimen> manager = new SimpleEvolutionManager<>(
                    evolution,
                    evolutionSetUpPanel.getMaxGenerationLimit()
            );
            evolutionLogsPanel.setManager(manager, indexList, bestList, medianList, worstList);

            change(evolutionSetUpPanel, evolutionLogsPanel);
            evolutionThread = evolutionLogsPanel.startEvolution();
        });
    }

    /**
     * <p>
     *     Initialization of GameSetUpPanel.
     * </p>
     */
    private void initGameSetUpPanel() {
        gameSetUpPanel.getBackButton().addActionListener(e -> change(gameSetUpPanel, mainPanel));
        gameSetUpPanel.getStartButton().addActionListener(e -> {
            DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
            DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
            DefaultListModel<Integer> gameRound = new DefaultListModel<>();
            GUIPlayer player1 = new GUIPlayer();
            GUIPlayer player2 = new GUIPlayer();
            PDGame<GUIPlayer, GUIPlayer> game = new PDGame<>(player1, player2, 10);
            game.addGameObserver(new IGameObserver() {
                @Override
                public void gameStopped() {
                    player1.setStopFlag(true);
                    player2.setStopFlag(true);
                }

                @Override
                public void scoresAdded(Pair<Integer, Integer> scores) {
                    player1.setCooperateFlag(false);
                    player1.setDefectFlag(false);
                    player2.setCooperateFlag(false);
                    player2.setDefectFlag(false);
                    player1Scores.addElement(scores.getFirst());
                    player2Scores.addElement(scores.getSecond());
                    gameRound.addElement(player1.getScoreHistory().size());
                }
            });
            gameLogsPanel.setGame(game, player1Scores, player2Scores, gameRound);
            change(gameSetUpPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
        });
    }

    /**
     * <p>
     *     Initialization of EvolutionLogsPanel
     * </p>
     */
    private void initEvolutionLogsPanel() {
        evolutionLogsPanel.getNextButton().addActionListener(e -> {
            DefaultListModel<Integer> player1Scores = new DefaultListModel<>();
            DefaultListModel<Integer> player2Scores = new DefaultListModel<>();
            DefaultListModel<Integer> gameRound = new DefaultListModel<>();
            PDGame<GUIPlayer, AIPDPlayer> game = getGUIPlayerAIPDPlayerPDGame(evolutionLogsPanel.getManager().getEvolution().getBestSpecimen(), player1Scores, player2Scores, gameRound);
            gameLogsPanel.setGame(game, player1Scores, player2Scores, gameRound);
            change(evolutionLogsPanel, gameLogsPanel);
            gameThread = gameLogsPanel.startGame();
        });
        evolutionLogsPanel.getStopButton().addActionListener(e -> {
            if (evolutionThread != null) {
                evolutionLogsPanel.getManager().stopEvolution();
                try {
                    evolutionThread.join();
                }
                catch (InterruptedException ignored) {}
            }
        });
    }

    /**
     * <p>
     * Prepares PDGame for one GUI player and one AI player.
     * </p>
     *
     * @param network for AI player
     * @param player1Scores list model of player 1's previous scores
     * @param player2Scores list model of player 2's previous scores
     * @param gameRound list of game round indexes
     * @return prepared game
     */
    private static PDGame<GUIPlayer, AIPDPlayer> getGUIPlayerAIPDPlayerPDGame(SimpleNeuralNetworkSpecimen network, DefaultListModel<Integer> player1Scores, DefaultListModel<Integer> player2Scores, DefaultListModel<Integer> gameRound) {
        GUIPlayer player = new GUIPlayer();
        PDGame<GUIPlayer, AIPDPlayer> game = new PDGame<>(player, new AIPDPlayer(network), 16);
        game.addGameObserver(new IGameObserver() {
            @Override
            public void gameStopped() {
                player.setStopFlag(true);
            }

            @Override
            public void scoresAdded(Pair<Integer, Integer> scores) {
                player.setCooperateFlag(false);
                player.setDefectFlag(false);
                player1Scores.addElement(scores.getFirst());
                player2Scores.addElement(scores.getSecond());
                gameRound.addElement(player.getScoreHistory().size());
            }
        });
        return game;
    }

    /**
     * @return new IEvolution based on the parameters set on the input
     */
    private IEvolution<SimpleNeuralNetworkSpecimen> createEvolution(DefaultListModel<Integer> indexList, DefaultListModel<Double> bestList, DefaultListModel<Double> medianList, DefaultListModel<Double> worstList) {
        ISpecimenFactory<SimpleNeuralNetworkSpecimen> factory = new ElmanNeuralNetworkFactory();
        IGame<AIPDPlayer, AIPDPlayer> netVsNetGame = new PDGame<>(new AIPDPlayer(), new AIPDPlayer(), evolutionSetUpPanel.getGameIterations());
        IGame<AIPDPlayer, AbstractStrategyPlayer> netVsStratGame = new PDGame<>(new AIPDPlayer(), new RandomPlayer(), evolutionSetUpPanel.getGameIterations());
        IEvaluator<SimpleNeuralNetworkSpecimen> netVsNetEvaluator = new PDEvaluatorNetVsNet(netVsNetGame);
        IEvaluator<SimpleNeuralNetworkSpecimen> netVsStratEvaluator = new PDEvaluatorNetVsStrat(Constants.PD_STRATEGIES, netVsStratGame, 4);
        return new EvolutionGUI<>(
                new EvolutionLogs<>(
                        new SimpleEvolution<>(
                                evolutionSetUpPanel.getMutationChance(),
                                evolutionSetUpPanel.getMutationMagnitude(),
                                evolutionSetUpPanel.getGenerationSize(),
                                factory,
                                netVsNetEvaluator, netVsStratEvaluator
                        ), 5, Constants.DEFAULT_STORAGE
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
