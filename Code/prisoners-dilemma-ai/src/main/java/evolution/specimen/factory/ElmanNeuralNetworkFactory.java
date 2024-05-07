package evolution.specimen.factory;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import neuralNetwork.layer.ElmanNNLayer;
import neuralNetwork.layer.Layer;

/**
 * <p>
 *     Factory for creating simple Elman's neural network with predefined architecture.
 * </p>
 * Network has following architecture: 1 (input layer), 6, 10, 6, 1 (output layer).
 */
public class ElmanNeuralNetworkFactory implements ISpecimenFactory<SimpleNeuralNetworkSpecimen> {
    @Override
    public SimpleNeuralNetworkSpecimen create() {
        return new SimpleNeuralNetworkSpecimen(
                new ElmanNNLayer(1, 6, "sigmoid"),
                new ElmanNNLayer(6, 10, "sigmoid"),
                new ElmanNNLayer(10, 6, "sigmoid"),
                new Layer(6, 1, "sigmoid")
        );
    }
}