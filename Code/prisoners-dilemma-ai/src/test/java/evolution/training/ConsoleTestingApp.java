package evolution.training;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.PDGameLogs;
import game.player.AIPDPlayer;
import game.player.ConsolePDPlayer;
import neuralNetwork.INeuralNetwork;
import neuralNetwork.layer.ILayer;
import org.nd4j.linalg.api.ndarray.INDArray;
import utils.Constants;

/**
 * <p>
 *     Simple application for playing Prisoner's Dilemma game against loaded network in the console.
 * </p>
 */
public class ConsoleTestingApp {

    public static void main(String[] args) {
        INeuralNetwork network = new SimpleNeuralNetworkSpecimen(Constants.DEFAULT_TEST_STORAGE.resolve("bestInGen40.txt"));
        printNetwork(network);
        IGame<ConsolePDPlayer, AIPDPlayer> testGame = new PDGameLogs<>(new ConsolePDPlayer(), new AIPDPlayer(network), 12);
        testGame.run();
    }

    /**
     * <p>
     *     Prints all of the parameters of the neural network (weights, biases and etc).
     * </p>
     * @param network neural network to be printed
     */
    private static void printNetwork(INeuralNetwork network) {
        for (ILayer layer: network.getLayers()) {
            for (INDArray parameter: layer.getParameters()) {
                System.out.println(parameter);
            }
        }
    }
}
