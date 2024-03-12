package neuralNetwork;

import neuralNetwork.layer.Layer;
import org.nd4j.linalg.api.ndarray.INDArray;

public class NeuralNetwork implements INeuralNetwork {
    private Layer[] layers;

    @Override
    public INDArray process(INDArray input) {
        return null;
    }

    @Override
    public Layer[] getLayers() {
        return layers;
    }

    @Override
    public Layer getLayer(int index) {
        return layers[index];
    }

    @Override
    public void setLayer(int index, Layer layer) {
        layers[index] = layer;
    }

    @Override
    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    @Override
    public int getDepth() {
        return layers.length;
    }



/*    @Override
    public void setDepth() {

    }*/
}
