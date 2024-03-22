package evolution.specimen.evaulator;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.player.AIPlayer;

/**
 * <p>
 *     Evaluator for neural networks that play Prisoner's Dilemma.
 * </p>
 * @param <T> extends NeuralNetworkSpecimen
 * @param <D> extends AIPlayer
 */
public class PDEvaluator<T extends SimpleNeuralNetworkSpecimen, D extends AIPlayer> implements IEvaluator<T> {
    private final IGame<D, D> game;

    public PDEvaluator(IGame<D, D> game) {
        this.game = game;
    }

    @Override
    public int evaluate(T specimen1, T specimen2) {
        game.getPlayer1().setNeuralNetwork(specimen1);
        game.getPlayer2().setNeuralNetwork(specimen2);
        game.run();
        specimen1.addToFitness(game.getPlayer1().getScore());
        specimen2.addToFitness(game.getPlayer2().getScore());
        return game.getPlayer1().getScore() + game.getPlayer2().getScore();
    }
}
