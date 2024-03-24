package evolution.training;

import evolution.IEvolution;
import evolution.SimpleEvolutionLogs;
import evolution.manager.IEvolutionManager;
import evolution.manager.SimpleEvolutionManager;
import evolution.specimen.ISpecimenFactory;
import evolution.specimen.SimpleNeuralNetworkSpecimen;
import evolution.specimen.evaulator.IEvaluator;
import evolution.specimen.evaulator.PDEvaluator;
import game.IGame;
import game.PDGame;
import game.PDGameLogs;
import game.player.AIPDPlayer;
import game.player.ConsolePDPlayer;

/**
 * <p>
 *     Simple application for running evolution for training neural networks to play Prisoner's Dilemma.
 * </p>
 */
public class ConsoleTrainingApp {
    private static final double SMALL_MUTATION_CHANCE = 0.05;
    private static final int SMALL_MUTATION_MAGNITUDE = 1;
    private static final double BIG_MUTATION_CHANCE = 0.01;
    private static final int BIG_MUTATION_MAGNITUDE = 6;
    private static final int GENERATION_SIZE = 60;
    private static final int MAX_GENERATION_LIMIT = 100;
    private static final int GAME_ITERATIONS = 50;
    private static final int ACCEPTABLE_FITNESS = 5 * GAME_ITERATIONS * GENERATION_SIZE + 1;
    private static final boolean ONE_PARENT = false;

    public static void main(String[] args) {
        ISpecimenFactory<SimpleNeuralNetworkSpecimen> factory =
                () -> new SimpleNeuralNetworkSpecimen(GAME_ITERATIONS, GAME_ITERATIONS, 1);
        IGame<AIPDPlayer, AIPDPlayer> game = new PDGame<>(new AIPDPlayer(), new AIPDPlayer(), GAME_ITERATIONS);
        IEvolutionManager<SimpleNeuralNetworkSpecimen> manager = getEvolutionManager(game, factory);
        manager.runEvolution();

        for (int i = 0; i < manager.getGenerationsHistory().size(); i++) {
            System.out.println("Generation " + (i+1) + ": " + manager.getGenerationsHistory().get(i));
        }

        // test best player by yourself
        IGame<ConsolePDPlayer, AIPDPlayer> gameTest = new PDGameLogs<>(new ConsolePDPlayer(), new AIPDPlayer(manager.getEvolution().getBestSpecimen()), 10);
        gameTest.run();
    }

    /**
     * <p>
     *      Sets up evolution manager.
     * </p>
     * @param game model that simulates game
     * @param factory object for creating specimens for evolution
     * @return evolution manager ready for running evolution
     */
    private static IEvolutionManager<SimpleNeuralNetworkSpecimen> getEvolutionManager(IGame<AIPDPlayer, AIPDPlayer> game, ISpecimenFactory<SimpleNeuralNetworkSpecimen> factory) {
        IEvaluator<SimpleNeuralNetworkSpecimen> evaluator = new PDEvaluator<>(game);
        IEvolution<SimpleNeuralNetworkSpecimen> evolution = new SimpleEvolutionLogs<>(
                SMALL_MUTATION_CHANCE,
                SMALL_MUTATION_MAGNITUDE,
                BIG_MUTATION_CHANCE,
                BIG_MUTATION_MAGNITUDE,
                GENERATION_SIZE,
                factory,
                evaluator
        );
        evolution.setOneParent(ONE_PARENT);
        return new SimpleEvolutionManager<>(evolution, MAX_GENERATION_LIMIT, ACCEPTABLE_FITNESS);
    }
}
