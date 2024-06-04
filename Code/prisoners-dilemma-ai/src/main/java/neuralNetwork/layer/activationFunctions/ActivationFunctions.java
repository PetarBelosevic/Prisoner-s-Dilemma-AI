package neuralNetwork.layer.activationFunctions;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * <p>
 *     Contains activation functions for neural networks layers.
 * </p>
 */
public class ActivationFunctions {
    private static final Map<String, UnaryOperator<INDArray>> FUNCTIONS = new HashMap<>();

    static {
        FUNCTIONS.put("sigmoid", x -> Transforms.sigmoid(x, false));
        FUNCTIONS.put("identity", x -> Transforms.identity(x, false));
        FUNCTIONS.put("tanh", x -> Transforms.tanh(x, false));
        FUNCTIONS.put("step", x -> Transforms.step(x, false));
    }

    /**
     * <p>
     *     Returns requested function to apply on INDArray vector.
     * </p>
     * @param name name of the function (not case-sensitive)
     * @return requested function
     */
    public static UnaryOperator<INDArray> getFunction(String name) {
        return FUNCTIONS.get(name.toLowerCase());
    }
}
