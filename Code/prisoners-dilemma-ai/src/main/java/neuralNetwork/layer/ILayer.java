package neuralNetwork.layer;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * <p>
 *     Model of a neural network's layer
 * </p>
 */
public interface ILayer {

    INDArray getWeights();

    void setWeights(INDArray weights);

    INDArray getBiases();

    void setBiases(INDArray biases);

    /**
     * <p>
     *     Multiplies input with weights, adds biases and applies transition function.
     * </p>
     * @param input input to the layer
     * @return output of the layer
     */
    INDArray getOutput(INDArray input);
}
