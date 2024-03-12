package neuralNetwork.layer;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.nd4j.linalg.factory.Nd4j;

/**
 * <p>
 *     Simple implementation of an ILayer interface.
 * </p>
 */
public class Layer implements ILayer {
    INDArray weights;
    INDArray biases;
    INDArray result;

    public Layer(INDArray weights, INDArray biases) {
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
        return Transforms.sigmoid(weights.mmuli(input, result).addi(biases, result), false);
    }
}
