package neuralNetwork.layer;

import jdk.dynalink.NoSuchDynamicMethodException;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

/**
 * <p>
 *     Simple abstract implementation of an ILayer interface.
 * </p>
 * This class doesn't implement activation function.
 * This is responsibility of all non-abstract classes derived from this class.
 */
abstract class AbstractLayer implements ILayer {
    protected INDArray weights;
    protected INDArray biases;
    protected INDArray result;

    protected AbstractLayer(int currentLayerSize, int previousLayerSize) {
        this.weights = Nd4j.create(currentLayerSize, previousLayerSize);
        this.biases = Nd4j.create(currentLayerSize);
        this.result = Nd4j.create(currentLayerSize);
    }

    protected AbstractLayer(INDArray weights, INDArray biases) {
        this.weights = weights;
        this.biases = biases;
        this.result = Nd4j.create(biases.shape());
    }

    @Override
    public INDArray getWeights() {
        return weights;
    }

    @Override
    public void setWeights(INDArray weights) {
        this.weights = weights;
    }

    @Override
    public INDArray getBiases() {
        return biases;
    }

    @Override
    public void setBiases(INDArray biases) {
        this.biases = biases;
    }

    @Override
    public INDArray getOutput(INDArray input) {
        return weights.mmuli(input, result).addi(biases, result);
    }

    @Override
    public int getRowNumber() {
        return (int) biases.size(0);
    }

    @Override
    public int getColumnNumber() {
        return (int) weights.size(1);
    }
}
