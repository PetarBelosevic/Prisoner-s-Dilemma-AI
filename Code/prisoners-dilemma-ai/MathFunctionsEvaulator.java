package ui.evolution.specimen.evaulator;

import ui.evolution.specimen.SimpleNeuralNetworkSpecimen;
import ui.utils.Pair;

import java.util.List;

public class MathFunctionsEvaulator implements IEvaluator<SimpleNeuralNetworkSpecimen> {
    private final List<Pair<Double[], Double>> features;

    public MathFunctionsEvaulator(List<Pair<Double[], Double>> features) {
        this.features = features;
    }

    @Override
    public double evaluate(SimpleNeuralNetworkSpecimen specimen) {
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
}
