package utilities;

import neuralNetwork.layer.ILayer;

public class UtilityFunctions {
    /**
     * <p>
     *     Generates random mutation.
     * </p>
     * Mutation can be big, small or can be 0.
     *
     * @param smallMutationChance chance for small mutation to occur
     * @param smallMutationMagnitude magnitude of small mutation
     * @param bigMutationChance chance for big mutation to occur if small mutation didn't occur
     * @param bigMutationMagnitude magnitude of big mutation
     * @return random mutation
     */
    public static double generateMutation(double smallMutationChance, double smallMutationMagnitude, double bigMutationChance, double bigMutationMagnitude) {
        if (Math.random() < smallMutationChance) {
            return Math.random() * smallMutationMagnitude;
        }
        else if (Math.random() < bigMutationChance) {
            return Math.random() * bigMutationMagnitude;
        }
        return  0.0;
    }
}
