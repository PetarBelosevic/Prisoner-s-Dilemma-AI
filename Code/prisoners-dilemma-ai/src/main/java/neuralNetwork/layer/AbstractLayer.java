package neuralNetwork.layer;

import neuralNetwork.layer.activationFunctions.ActivationFunctions;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import utils.Constants;
import utils.UtilityFunctions;

import java.util.function.UnaryOperator;

/**
 * <p>
 *     Simple abstract implementation of an ILayer interface.
 * </p>
 * This class implements but doesn't use activation function.
 */
abstract class AbstractLayer implements ILayer {
    private INDArray[] parameters = new INDArray[2];
    private INDArray result;
    private UnaryOperator<INDArray> activationFunction;
    private String activationFunctionName;

    protected AbstractLayer(String activationFunctionName) {
        initialize(activationFunctionName);
    }

    protected AbstractLayer(int previousLayerSize, int currentLayerSize, String activationFunctionName) {
        this.parameters[0] = Nd4j.create(currentLayerSize).castTo(DataType.DOUBLE);
        this.parameters[1] = Nd4j.create(currentLayerSize, previousLayerSize).castTo(DataType.DOUBLE);
        this.result = Nd4j.create(parameters[0].shape()).castTo(DataType.DOUBLE);
        initialize(activationFunctionName);
        randomizeWeightsAndBiases();
    }

    /**
     * <p>
     *     Initializes result INDArray and activation function.
     * </p>
     */
    private void initialize(String activationFunctionName) {
        this.activationFunctionName = activationFunctionName;
        activationFunction = ActivationFunctions.getFunction(activationFunctionName);
    }

    @Override
    public INDArray[] getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(INDArray[] newParameters) {
        this.parameters = newParameters;
        this.result = Nd4j.create(parameters[0].shape()).castTo(DataType.DOUBLE);
    }

    /**
     * @return result INDArray after applying activation function on it
     */
    protected INDArray applyActivationFunction() {
        return activationFunction.apply(result);
    }

    /**
     * <p>
     *     Randomizes values of biases and weights in this layer.
     * </p>
     */
    private void randomizeWeightsAndBiases() {
        for (INDArray parameter: parameters) {
            for (int j = 0; j < parameter.length(); j++) {
                parameter.putScalar(j, UtilityFunctions.getRandom(Constants.RANDOMNESS_LEVEL));
            }
        }
    }

    @Override
    public INDArray getOutput(INDArray input) {
        return parameters[1].mmuli(input, result).addi(parameters[0], result);
    }

    @Override
    public int getNumberOfInputs() {
        return (int) parameters[1].size(1);
    }

    @Override
    public int getNumberOfOutputs() {
        return (int) parameters[0].size(0);
    }

    @Override
    public String getActivationFunctionName() {
        return activationFunctionName;
    }

    /**
     * @return INDArray for storing result of input processing
     */
    protected INDArray getResult() {
        return result;
    }
}
