package evolution.specimen.factory;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import neuralNetwork.layer.ElmanNNLayer;
import neuralNetwork.layer.Layer;

/**
 * <p>
 *     Factory for creating simple Elman's neural network with predefined architecture.
 * </p>
 * Network has following architecture: 1 (input layer), 4, 2, 1 (output layer).
 * The first hidden layer has hidden context.
 */
public class ElmanNeuralNetworkFactory implements ISpecimenFactory<SimpleNeuralNetworkSpecimen> {
    @Override
    public SimpleNeuralNetworkSpecimen create() {
        return new SimpleNeuralNetworkSpecimen(
                new ElmanNNLayer(1, 4, "sigmoid"),
                new Layer(4, 2, "sigmoid"),
                new Layer(2, 1, "sigmoid")
        );
    }
}
