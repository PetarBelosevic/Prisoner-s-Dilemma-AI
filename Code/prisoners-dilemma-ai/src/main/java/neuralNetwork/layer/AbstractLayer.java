package neuralNetwork.layer;

import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * <p>
 *     Simple abstract implementation of an ILayer interface.
 * </p>
 * This class doesn't implement activation function.
 */
abstract class AbstractLayer implements ILayer {
    protected INDArray weights;
    protected INDArray biases;
    protected INDArray result;

    protected AbstractLayer(int currentLayerSize, int previousLayerSize) {
        this.weights = Nd4j.create(currentLayerSize, previousLayerSize).castTo(DataType.DOUBLE);
        this.biases = Nd4j.create(currentLayerSize).castTo(DataType.DOUBLE);
        this.result = Nd4j.create(currentLayerSize).castTo(DataType.DOUBLE);
    }

    protected AbstractLayer(INDArray weights, INDArray biases) {
        this.weights = weights.castTo(DataType.DOUBLE);
        this.biases = biases.castTo(DataType.DOUBLE);
        this.result = Nd4j.create(biases.shape()).castTo(DataType.DOUBLE);
    }

    @Override
    public INDArray getWeights() {
        return weights;
    }

    @Override
    public void setWeights(INDArray weights) {
        this.weights = weights.castTo(DataType.DOUBLE);
    }

    @Override
    public INDArray getBiases() {
        return biases;
    }

    @Override
    public void setBiases(INDArray biases) {
        this.biases = biases.castTo(DataType.DOUBLE);
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
