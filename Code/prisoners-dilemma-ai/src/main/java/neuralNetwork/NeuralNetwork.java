package neuralNetwork;

import neuralNetwork.layer.ILayer;
import neuralNetwork.layer.SigmoidLayer;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * <p>
 *     Simple implementation of INeuralNetwork interface.
 * </p>
 */
public class NeuralNetwork implements INeuralNetwork {
    private static final double RANDOMNESS_LEVEL = 20;
    private ILayer[] layers;
    private INDArray input;
    private double[] outputArray;

    public NeuralNetwork(int... layerSizes) {
        this.layers = new ILayer[layerSizes.length - 1];
        this.input = Nd4j.create(layerSizes[0]).castTo(DataType.DOUBLE);
        this.outputArray = new double[layerSizes[layerSizes.length-1]];

        for (int i = 0; i < layerSizes.length - 1; i++) {
            this.layers[i] = new SigmoidLayer(layerSizes[i+1], layerSizes[i]);
        }
        randomize();
    }

    protected NeuralNetwork() {}

    /**
     * <p>
     *     Randomizes values of biases and weights in neural network.
     * </p>
     */
    private void randomize() {
        for (ILayer layer : layers) {
            for (int j = 0; j < layer.getBiases().length(); j++) {
                layer.getBiases().putScalar(j, (Math.random() * RANDOMNESS_LEVEL) - RANDOMNESS_LEVEL / 2);
            }
            for (int j = 0; j < layer.getWeights().length(); j++) {
                layer.getWeights().putScalar(j, (Math.random() * RANDOMNESS_LEVEL) - RANDOMNESS_LEVEL / 2);
            }
        }
    }

    @Override
    public INDArray process(INDArray input) {
        for (ILayer layer : layers) {
            input = layer.getOutput(input);
        }
        return input;
    }

    @Override
    public double[] process(double[] inputArray) {
        for(int i = 0; i < inputArray.length; i++) {
            input.putScalar(i, inputArray[i]);
        }

        INDArray output = process(input);

        for(int i = 0; i < output.length(); i++) {
            outputArray[i] = output.getDouble(i);
        }

        return outputArray;
    }

    @Override
    public ILayer[] getLayers() {
        return layers;
    }

    @Override
    public ILayer getLayer(int index) {
        return layers[index];
    }

    @Override
    public void setAllLayers(ILayer[] layers) {
        this.layers = layers;
        this.input = Nd4j.create(layers[0].getRowNumber()).castTo(DataType.DOUBLE);
        this.outputArray = new double[layers[layers.length-1].getColumnNumber()];
    }

    @Override
    public int getDepth() {
        return layers.length + 1;
    }
}
