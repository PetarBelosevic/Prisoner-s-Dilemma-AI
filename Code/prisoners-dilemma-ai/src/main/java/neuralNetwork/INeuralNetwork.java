package neuralNetwork;

import neuralNetwork.layer.ILayer;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * <p>
 *     Interface for fully connected neural network.
 * </p>
 */
public interface INeuralNetwork {
    // TODO remove?
    /**
     * <p>
     *     Processes input through entire neural network
     * </p>
     * @param input input to a neural network
     * @return output of a neural network
     */
    INDArray process(INDArray input);
    double[] process(Double[] input);

    /**
     * @return array of layers from this network
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
     *     Replaces all layers with new layers
     * </p>
     * @param layers new layers
     */
    void setAllLayers(ILayer[] layers);

    /**
     * @return number of layers in this neural network
     */
    int getDepth();

    /**
     * <p>
     *     Clears data from any cached data (e.g. context).
     * </p>
     */
    void clearNetwork();
}
