package evolution.specimen.evaulator;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.player.AIPDPlayer;

/**
 * <p>
 *     Evaluator for neural networks that play Prisoner's Dilemma.
 * </p>
 * @param <T> extends NeuralNetworkSpecimen
 */
public class PDEvaluator<T extends SimpleNeuralNetworkSpecimen> implements IEvaluator<T> {
    private final IGame<? extends AIPDPlayer, ? extends AIPDPlayer> game;

    public PDEvaluator(IGame<? extends AIPDPlayer, ? extends AIPDPlayer> game) {
        this.game = game;
    }

    @Override
    public int evaluate(T specimen1, T specimen2) {
        game.getPlayer1().setNeuralNetwork(specimen1);
        game.getPlayer2().setNeuralNetwork(specimen2);
        game.run();
        if (specimen1 == specimen2) {
            specimen1.addToFitness((game.getPlayer1().getScore() + game.getPlayer2().getScore()) / 2);
            return (game.getPlayer1().getScore() + game.getPlayer2().getScore()) / 2;
        }
        specimen1.addToFitness(game.getPlayer1().getScore());
        specimen2.addToFitness(game.getPlayer2().getScore());
        return game.getPlayer1().getScore() + game.getPlayer2().getScore();
    }
}
