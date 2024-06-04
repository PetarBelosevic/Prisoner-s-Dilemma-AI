package evolution.specimen.evaulator;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import utils.Pair;

import java.util.List;

/**
 * <p>
 *     Evaluator for mathematical functions.
 * </p>
 * Evaluator uses prepared pairs of inputs and expected outputs.
 */
public class MathFunctionEvaluator implements IEvaluator<SimpleNeuralNetworkSpecimen> {
    private final List<Pair<Double[], Double>> features;

    public MathFunctionEvaluator(List<Pair<Double[], Double>> features) {
        this.features = features;
    }

    /**
     * <p>
     *     Method takes neural network, feeds all inputs to it and calculates error based on the given outputs and expected values.
     * </p>
     * @param specimen neural network to test
     * @return negated total error
     */
    private double evaluateOne(SimpleNeuralNetworkSpecimen specimen) {
        double total = 0;
        for (var feature: features) {
            double x = specimen.process(feature.getFirst())[0];
            double dx = (x - feature.getSecond()) * (x - feature.getSecond());
            total += dx;
        }
        total /= features.size();

        specimen.addToFitness(-total);
        return -total;
    }

    @Override
    public double evaluate(List<SimpleNeuralNetworkSpecimen> population) {
        double totalFitness = 0;
        for (var specimen: population) {
            totalFitness += evaluateOne(specimen);
        }
        return totalFitness;
    }

    @Override
    public double maxPossibleScore(int populationSize) {
        return 0.0;
    }
}
