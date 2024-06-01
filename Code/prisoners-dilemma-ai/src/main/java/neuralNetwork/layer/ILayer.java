package neuralNetwork.layer;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * <p>
 *     Model of a fully connected neural network's layer.
 * </p>
 * Layers are meant to be mutable.
 * Every layer must have constructor with activation function name as single parameter.
 */
public interface ILayer {
    /**
     * <p>
     *     Vector of biases is always on index 0 and matrix of weights is always on index 1.
     * </p>
     * @return array of matrices in this layer
     */
    INDArray[] getParameters();

    /**
     * <p>
     *     Sets new parameters for this layer.
     * </p>
     * @param newParameters new parameters
     */
    void setParameters(INDArray[] newParameters);

    /**
     * <p>
     *     Multiplies input with weights, adds biases and applies transition function.
     * </p>
     * Input data must be of type double.
     *
     * @param input input to the layer
     * @return output of the layer
     */
    INDArray getOutput(INDArray input);

    /**
     * @return number of inputs in the layer
     */
    int getNumberOfInputs();

    /**
     * @return number of outputs of the layer
     */
    int getNumberOfOutputs();

    /**
     * @return name of activation function used in this layer
     */
    String getActivationFunctionName();

    /**
     * <p>
     *     Clears layer from any cached data (e.g. context)
     * </p>
     */
    void clearLayer();

    /**
     * @return exact copy of this object
     */
    ILayer copy();
}
