package evolution.specimen.evaulator;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.player.AIPDPlayer;
import game.player.strategies.AbstractStrategyPlayer;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 */
public class PDEvaluatorNetVsStrat implements IEvaluator<SimpleNeuralNetworkSpecimen> {
    private final Collection<AbstractStrategyPlayer> strategies;
    private final IGame<? extends AIPDPlayer, AbstractStrategyPlayer> game;

    public PDEvaluatorNetVsStrat(Collection<AbstractStrategyPlayer> strategies, IGame<? extends AIPDPlayer, AbstractStrategyPlayer> game) {
        this.strategies = strategies;
        this.game = game;
    }

    /**
     * <p>
     *     Runs game with given neural network against given strategy.
     * </p>
     * @param network network to play the game
     * @param strategy strategy player to play the game
     * @return score scored by the network
     */
    private double evaluateOne(SimpleNeuralNetworkSpecimen network, AbstractStrategyPlayer strategy) {
        game.getPlayer1().setNeuralNetwork(network);
        game.setPlayer2(strategy);
        game.run();
        double score = game.getPlayer1().getScore();
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
}
