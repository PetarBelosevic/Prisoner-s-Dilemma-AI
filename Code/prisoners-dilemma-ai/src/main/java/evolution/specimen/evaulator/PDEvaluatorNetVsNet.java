package evolution.specimen.evaulator;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.player.AIPDPlayer;

import java.util.List;

/**
 * <p>
 *     Evaluator for neural networks that play Prisoner's Dilemma.
 * </p>
 */
public class PDEvaluatorNetVsNet implements IEvaluator<SimpleNeuralNetworkSpecimen> {
    private final IGame<? extends AIPDPlayer, ? extends AIPDPlayer> game;

    public PDEvaluatorNetVsNet(IGame<? extends AIPDPlayer, ? extends AIPDPlayer> game) {
        this.game = game;
    }

    /**
     * <p>
     *     Evaluates two specimens in their interaction.
     * </p>
     * @param specimen1 specimen 1
     * @param specimen2 specimen 2
     * @return combined fitness given to both specimens
     */
    private double evaluateTwo(SimpleNeuralNetworkSpecimen specimen1, SimpleNeuralNetworkSpecimen specimen2) {
        game.getPlayer1().setNeuralNetwork(specimen1);
        game.getPlayer2().setNeuralNetwork(specimen2);
        game.run();
        if (specimen1 == specimen2) {
            specimen1.addToFitness((double) (game.getPlayer1().getScore() + game.getPlayer2().getScore()) / 2);
            return (game.getPlayer1().getScore() + game.getPlayer2().getScore()) / 2.;
        }
        specimen1.addToFitness(game.getPlayer1().getScore());
        specimen2.addToFitness(game.getPlayer2().getScore());
        return game.getPlayer1().getScore() + game.getPlayer2().getScore();
    }

    @Override
    public double evaluate(List<SimpleNeuralNetworkSpecimen> population) {
        double totalFitness = 0;
        for (int i = 0; i < population.size(); i++) {
            for (int j = i; j < population.size(); j++) {
                totalFitness += evaluateTwo(population.get(i), population.get(j));
            }
        }
        return totalFitness;
    }
}
