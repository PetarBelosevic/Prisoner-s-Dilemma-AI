package neuralNetwork;

import neuralNetwork.layer.ILayer;
import neuralNetwork.layer.Layer;
import org.nd4j.linalg.api.buffer.DataType;
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
    private double[] outputArray;

    public NeuralNetwork(ILayer... layers) {
//        this.layers = layers;
        this.layers = new ILayer[layers.length];
        for (int i = 0; i < layers.length; i++) {
            this.layers[i] = layers[i].copy();
        }
        this.input = Nd4j.create(layers[0].getNumberOfInputs()).castTo(DataType.DOUBLE);
        this.outputArray = new double[layers[layers.length-1].getNumberOfOutputs()];
    }

    public NeuralNetwork(int... layerSizes) {
        this.layers = new ILayer[layerSizes.length - 1];
        this.input = Nd4j.create(layerSizes[0]).castTo(DataType.DOUBLE);
        this.outputArray = new double[layerSizes[layerSizes.length-1]];

        for (int i = 0; i < layerSizes.length - 1; i++) {
            this.layers[i] = new Layer(layerSizes[i], layerSizes[i+1], "sigmoid");
        }
    }

    protected NeuralNetwork() {}

    @Override
    public INDArray process(INDArray input) {
        for (ILayer layer : layers) {
            input = layer.getOutput(input);
        }
        return input;
    }

    @Override
    public double[] process(Double[] inputArray) {
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
        this.input = Nd4j.create(layers[0].getNumberOfInputs()).castTo(DataType.DOUBLE);
        this.outputArray = new double[layers[layers.length-1].getNumberOfOutputs()];
    }

    @Override
    public int getDepth() {
        return layers.length + 1;
    }

    @Override
    public void clearNetwork() {
        for (ILayer layer: layers) {
            layer.clearLayer();
        }
    }
}
