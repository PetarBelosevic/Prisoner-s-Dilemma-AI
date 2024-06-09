package utils;

import java.util.Random;

/**
 * <p>
 *     Class with commonly shared functionalities.
 * </p>
 */
public class UtilityFunctions {
    private static final Random RANDOM = new Random();

    /**
     * <p>
     *     Generates random mutation.
     * </p>
     * Mutation is based on the Gaussian normal distribution.
     *
     * @param mutationChance chance for a mutation to occur
     * @param mutationMagnitude magnitude of mutation
     * @return random mutation
     */
    public static double generateMutation(double mutationChance, double mutationMagnitude) {
        if (Math.random() < mutationChance) {
            return RANDOM.nextGaussian(0.0, mutationMagnitude);
        }
        return  0.0;
    }

    /**
     * <p>
     *     Generates random number form Gaussian normal distribution.
     * </p>
     * @param dev spread of the distribution
     * @return random number
     */
    public static double getRandom(double dev) {
        return RANDOM.nextGaussian(0, dev);
    }
}
