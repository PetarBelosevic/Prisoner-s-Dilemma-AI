package neuralNetwork;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

public class TestNeuralNetwork {
    @Test
    void testNeuralNetworkWorks1() {
        INeuralNetwork network = new NeuralNetwork(2, 2, 1);
        INDArray array = Nd4j.createFromArray((float) 1.0, (float) 1.0);
        assertDoesNotThrow(() -> System.out.println(network.process(array)));
    }

    @Test
    void testNeuralNetworkWorks2() {
        INeuralNetwork network = new NeuralNetwork(3, 2, 5);
        double[] array = {2.0, 1.0, 1.0};
        assertDoesNotThrow(() -> System.out.println(Arrays.toString(network.process(array))));
    }
}
