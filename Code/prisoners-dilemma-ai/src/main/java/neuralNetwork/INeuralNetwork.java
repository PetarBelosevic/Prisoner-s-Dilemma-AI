package neuralNetwork;

import neuralNetwork.layer.ILayer;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * <p>
 *     Interface for fully connected neural network
 * </p>
 */
public interface INeuralNetwork {
    /**
     * <p>
     *     Processes input through entire neural network
     * </p>
     * @param input input to a neural network
     * @return output of a neural network
     */
    INDArray process(INDArray input);
    double[] process(double[] input);

    /**
     * @return array of layers in this network
     */
    ILayer[] getLayers();

    /**
     * <p>
     *     Returns layer of this network at given index.
     * </p>
     * Layers are zero-indexed.
     * Layer with index 0 is input layer.
     *
     * @param index index of layer
     * @return layer at given index
     */
    ILayer getLayer(int index);

    /**
     * <p>
     *     Sets Layer at given index.
     * </p>
     * @param index where new layer is being set
     * @param layer that is to be set
     */
    void setLayer(int index, ILayer layer);

    /**
     * <p>
     *     Replaces all layers with new layers
     * </p>
     * @param layers new layers
     */
    void setAllLayers(ILayer[] layers);

    /**
     * @return number of layers in this neural network
     */
    int getDepth();
}
