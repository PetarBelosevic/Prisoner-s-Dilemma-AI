package neuralNetwork.layer;

import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import utilities.Constants;

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
            getParameters()[2].putScalar(j, (Math.random() * Constants.RANDOMNESS_LEVEL) - Constants.RANDOMNESS_LEVEL / 2);
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
        for (int i = 0; i < getResult().length(); i++) {
            context.putScalar(i, getResult().getDouble(i));
        }
        return getResult();
    }

    @Override
    public void clearLayer() {
        context.assign(0);
    }
}
