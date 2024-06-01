package neuralNetwork.layer;

import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import utils.Constants;
import utils.UtilityFunctions;

import java.util.Arrays;

/**
 * <p>
 *     Layer of neural network with hidden context.
 * </p>
 */
public class ElmanNNLayer extends AbstractLayer {
    private INDArray context;

    public ElmanNNLayer(String activationFunctionName) {
        super(activationFunctionName);
    }

    public ElmanNNLayer(int previousLayerSize, int currentLayerSize, String activationFunctionName) {
        super(previousLayerSize, currentLayerSize, activationFunctionName);
        context = Nd4j.create(currentLayerSize).castTo(DataType.DOUBLE);
        for (int j = 0; j < context.length(); j++) {
            context.putScalar(j, 0.5);
        }
        setParameters(Arrays.copyOf(getParameters(), 3));
        getParameters()[2] = Nd4j.create(currentLayerSize, currentLayerSize).castTo(DataType.DOUBLE);
        randomizeContextWeights();
    }

    /**
     * <p>
     *     Randomizes values of biases and weights in this layer.
     * </p>
     */
    private void randomizeContextWeights() {
        for (int j = 0; j < getParameters()[2].length(); j++) {
            getParameters()[2].putScalar(j, UtilityFunctions.getRandom(Constants.RANDOMNESS_LEVEL));
        }
    }

    @Override
    public void setParameters(INDArray[] newParameters) {
        super.setParameters(newParameters);
        context = Nd4j.create(getNumberOfOutputs()).castTo(DataType.DOUBLE);
    }

    @Override
    public INDArray getOutput(INDArray input) {
        getParameters()[2].mmuli(context, context);
        super.getOutput(input).addi(context, getResult());

        INDArray res = applyActivationFunction();
        for (int i = 0; i < getResult().length(); i++) {
            context.putScalar(i, res.getDouble(i));
        }
        
        return res;
    }

    @Override
    public void clearLayer() {
        context.assign(0.5);
    }

    @Override
    public ILayer copy() {
        return new ElmanNNLayer(getNumberOfInputs(), getNumberOfOutputs(), getActivationFunctionName());
    }
}
