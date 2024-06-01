package evolution.training;

import evolution.IEvolution;
import evolution.EvolutionLogs;
import evolution.SimpleEvolution;
import evolution.manager.IEvolutionManager;
import evolution.manager.SimpleEvolutionManager;
import evolution.specimen.evaulator.MathFunctionEvaluator;
import evolution.specimen.factory.ISpecimenFactory;
import evolution.specimen.SimpleNeuralNetworkSpecimen;
import evolution.specimen.evaulator.IEvaluator;
import game.IGame;
import game.PDGame;
import game.player.AIPDPlayer;
import neuralNetwork.layer.Layer;
import utils.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     Simple application for running evolution for training neural networks to play Prisoner's Dilemma.
 * </p>
 */
public class ConsoleTrainingApp {
    private static final double SMALL_MUTATION_CHANCE = 0.1;
    private static final double SMALL_MUTATION_MAGNITUDE = 0.1;
    private static final double BIG_MUTATION_CHANCE = 0.01;
    private static final double BIG_MUTATION_MAGNITUDE = 6;
    private static final int GENERATION_SIZE = 20;
    private static final int MAX_GENERATION_LIMIT = 4000;
    private static final int GAME_ITERATIONS = 30;
    private static final int ACCEPTABLE_FITNESS = 5 * GAME_ITERATIONS * GENERATION_SIZE + 1;

    public static void main(String[] args) {
        ISpecimenFactory<SimpleNeuralNetworkSpecimen> factory = () -> new SimpleNeuralNetworkSpecimen(
//                new ElmanNNLayer(1, 3, "sigmoid"),
                new Layer(1, 5, "sigmoid"),
                new Layer(5, 5, "sigmoid"),
//                new Layer(4, 2, "sigmoid"),
                new Layer(5, 1, "identity")
        );
        IGame<AIPDPlayer, AIPDPlayer> game = new PDGame<>(new AIPDPlayer(), new AIPDPlayer(), GAME_ITERATIONS);
        IEvolutionManager<SimpleNeuralNetworkSpecimen> manager = getEvolutionManager(game, factory);
        manager.runEvolution();

        for (int i = 0; i < manager.getGenerationsHistory().size(); i++) {
            System.out.println("Generation " + i + ": " + manager.getGenerationsHistory().get(i));
        }

        // test best player by yourself
//        IGame<ConsolePDPlayer, AIPDPlayer> gameTest = new PDGameLogs<>(new ConsolePDPlayer(), new AIPDPlayer(manager.getEvolution().getBestSpecimen()), 10);
//        gameTest.run();
    }

    /**
     * <p>
     *      Sets up evolution manager.
     * </p>
     * @param game model that simulates game
     * @param factory object for creating specimens for evolution
     * @return evolution manager ready for running evolution
     */
    public static IEvolutionManager<SimpleNeuralNetworkSpecimen> getEvolutionManager(IGame<AIPDPlayer, AIPDPlayer> game, ISpecimenFactory<SimpleNeuralNetworkSpecimen> factory) {
//        IEvaluator<SimpleNeuralNetworkSpecimen> evaluator = new PDEvaluator(game);
        IEvaluator<SimpleNeuralNetworkSpecimen> evaluator;

        try {
            evaluator = new MathFunctionEvaluator(loadFile(Path.of("sine_train.txt")));
        }
        catch (IOException e) {
            System.out.println("Error!");
            throw new RuntimeException(e);
        }

        IEvolution<SimpleNeuralNetworkSpecimen> evolution = new EvolutionLogs<>(new SimpleEvolution<>(
                SMALL_MUTATION_CHANCE,
                SMALL_MUTATION_MAGNITUDE,
                BIG_MUTATION_CHANCE,
                BIG_MUTATION_MAGNITUDE,
                GENERATION_SIZE,
                factory,
                evaluator
        ), 100);
        return new SimpleEvolutionManager<>(evolution, MAX_GENERATION_LIMIT, ACCEPTABLE_FITNESS);
    }

    private static List<Pair<Double[], Double>> loadFile(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        List<Pair<Double[], Double>> ret = new LinkedList<>();
        int n = lines.get(0).split(",").length - 1;

        for (int i = 1; i < lines.size(); i++) {
            String[] elements = lines.get(i).split(",");

            Double[] input = new Double[n];
            for (int j = 0; j < input.length; j++) {
                input[j] = Double.parseDouble(elements[j]);
            }

            Double result = Double.parseDouble(elements[elements.length - 1]);

            ret.add(new Pair<>(input, result));
        }

        return ret;
    }
}
