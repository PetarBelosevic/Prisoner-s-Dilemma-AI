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
     * Mutation can be big, small or can be 0.
     *
     * Mutation is based on the Gaussian normal distribution.
     *
     * @param smallMutationChance chance for small mutation to occur
     * @param smallMutationMagnitude magnitude of small mutation
     * @param bigMutationChance chance for big mutation to occur if small mutation didn't occur
     * @param bigMutationMagnitude magnitude of big mutation
     * @return random mutation
     */
    public static double generateMutation(double smallMutationChance, double smallMutationMagnitude, double bigMutationChance, double bigMutationMagnitude) {
//        if (Math.random() < smallMutationChance) {
//            return Math.random() * smallMutationMagnitude;
//        }
//        else if (Math.random() < bigMutationChance) {
//            return Math.random() * bigMutationMagnitude;
//        }

        if (Math.random() < smallMutationChance) {
            return RANDOM.nextGaussian(0.0, smallMutationMagnitude);
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
//        return (Math.random() * Constants.RANDOMNESS_LEVEL) - Constants.RANDOMNESS_LEVEL / 2;
    }
}
