package evolution.specimen.evaulator;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.PDConstants;
import game.player.AIPDPlayer;
import game.player.strategies.AbstractStrategyPlayer;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *     Evaluator for neural networks that play Prisoner's Dilemma.
 * </p>
 * Evaluator puts each neural network in Prisoner's Dilemma game against every strategy that evaluator has.
 * Fitness of each network is calculated as combined score that network accumulated playing the game.
 * Each network plays against each strategy for a defined number of times (the default is 5 times).
 */
public class PDEvaluatorNetVsStrat implements IEvaluator<SimpleNeuralNetworkSpecimen> {
    private final Collection<AbstractStrategyPlayer> strategies;
    private final IGame<? extends AIPDPlayer, AbstractStrategyPlayer> game;
    private final int repetitions;

    public PDEvaluatorNetVsStrat(Collection<AbstractStrategyPlayer> strategies, IGame<? extends AIPDPlayer, AbstractStrategyPlayer> game, int repetitions) {
        this.strategies = strategies;
        this.game = game;
        this.repetitions = repetitions;
    }

    public PDEvaluatorNetVsStrat(Collection<AbstractStrategyPlayer> strategies, IGame<? extends AIPDPlayer, AbstractStrategyPlayer> game) {
        this(strategies, game, 5);
    }

    /**
     * <p>
     *     Runs a game with given neural network against given strategy.
     * </p>
     * @param network network to play the game
     * @param strategy strategy player to play the game
     * @return score scored by the network
     */
    private double evaluateOne(SimpleNeuralNetworkSpecimen network, AbstractStrategyPlayer strategy) {
        game.getPlayer1().setNeuralNetwork(network);
        game.setPlayer2(strategy);
        double score = 0.0;

        for (int i = 0; i < repetitions; i++) {
            game.run();
            score += game.getPlayer1().getScore();
        }

        score /= repetitions;
        network.addToFitness(score);
        return score;
    }

    @Override
    public double evaluate(List<SimpleNeuralNetworkSpecimen> population) {
        double totalFitness = 0;
        for (var network: population) {
            for (var strategy: strategies) {
                totalFitness += evaluateOne(network, strategy);
            }
        }
        return totalFitness;
    }

    @Override
    public double maxPossibleScore(int populationSize) {
        return PDConstants.D_C * strategies.size() * game.getIterations();
    }
}
