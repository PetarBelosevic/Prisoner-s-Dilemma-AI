package neuralNetwork.layer;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * <p>
 *     Layer with sigmoid activation function.
 * </p>
 */
public class SigmoidLayer extends AbstractLayer {
    public SigmoidLayer(INDArray weights, INDArray biases) {
        super(weights, biases);
    }

    public SigmoidLayer(int currentLayerSize, int previousLayerSize) {
        super(currentLayerSize, previousLayerSize);
    }

    @Override
    public INDArray getOutput(INDArray input) {
        return Transforms.sigmoid(super.getOutput(input), false);
    }
}
