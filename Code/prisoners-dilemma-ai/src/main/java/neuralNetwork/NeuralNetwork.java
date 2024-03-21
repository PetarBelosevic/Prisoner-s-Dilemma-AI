package neuralNetwork;

import neuralNetwork.layer.ILayer;
import neuralNetwork.layer.SigmoidLayer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * <p>
 *     Simple implementation of INeuralNetwork interface.
 * </p>
 */
public class NeuralNetwork implements INeuralNetwork {
    private ILayer[] layers;
    private INDArray input;
    private INDArray output;
    private double[] outputArray;

    public NeuralNetwork(ILayer[] layers) {
        this.layers = layers;
    }

    public NeuralNetwork(int... layerSizes) {
        this.layers = new ILayer[layerSizes.length - 1];
        this.input = Nd4j.create(layerSizes[0]);
        this.outputArray = new double[layerSizes[layerSizes.length-1]];

        for (int i = 0; i < layerSizes.length - 1; i++) {
            this.layers[i] = new SigmoidLayer(layerSizes[i+1], layerSizes[i]);
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

        output = process(input);

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
    public void setLayer(int index, ILayer layer) {
        layers[index] = layer;
    }

    @Override
    public void setAllLayers(ILayer[] layers) {
        this.layers = layers;
    }

    @Override
    public int getDepth() {
        return layers.length;
    }
}
