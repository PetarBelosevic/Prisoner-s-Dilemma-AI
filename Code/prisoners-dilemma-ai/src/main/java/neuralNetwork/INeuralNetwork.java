package neuralNetwork;

import neuralNetwork.layer.Layer;
import org.nd4j.linalg.api.ndarray.INDArray;

public interface INeuralNetwork {
    /**
     * <p>
     *     Processes input through entire neural network
     * </p>
     * @param input input to a neural network
     * @return output of a neural network
     */
    INDArray process(INDArray input);
    Layer[] getLayers();
    Layer getLayer(int index);
    void setLayer(int index, Layer layer);
    void setLayers(Layer[] layers);
    int getDepth();

    //    void setDepth();
}
