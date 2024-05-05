package neuralNetwork.layer;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * <p>
 *     Layer that uses activation function.
 * </p>
 */
public class Layer extends AbstractLayer {
    public Layer(String activationFunctionName) {
        super(activationFunctionName);
    }

    public Layer(int previousLayerSize, int currentLayerSize, String activationFunctionName) {
        super(previousLayerSize, currentLayerSize, activationFunctionName);
    }

    @Override
    public INDArray getOutput(INDArray input) {
        super.getOutput(input);
        return applyActivationFunction();
    }

    @Override
    public void clearLayer() {}
}
