package neuralNetwork.layer;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * <p>
 *     Model of a fully connected neural network's layer
 * </p>
 */
public interface ILayer {

    /**
     * @return matrix of weights in this layer
     */
    INDArray getWeights();

    /**
     * @param weights new matrix of weights
     */
    void setWeights(INDArray weights);

    /**
     * @return vector of biases for this layer
     */
    INDArray getBiases();

    /**
     * @param biases new vector of biases for this layer
     */
    void setBiases(INDArray biases);

    /**
     * <p>
     *     Multiplies input with weights, adds biases and applies transition function.
     * </p>
     * @param input input to the layer
     * @return output of the layer
     */
    INDArray getOutput(INDArray input);

    /**
     * <p>
     *     Returns number of rows in layer matrix.
     * </p>
     * Equivalent of number of inputs in the layer.
     *
     * @return number of rows in layer matrix
     */
    int getRowNumber();

    /**
     * <p>
     *     Returns number of columns in layer matrix.
     * </p>
     * Equivalent of number of outputs of the layer.
     *
     * @return number of columns in layer matrix
     */
    int getColumnNumber();
}
