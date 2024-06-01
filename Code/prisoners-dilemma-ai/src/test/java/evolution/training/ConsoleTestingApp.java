package evolution.training;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.PDGameLogs;
import game.player.AIPDPlayer;
import game.player.ConsolePDPlayer;
import neuralNetwork.INeuralNetwork;
import neuralNetwork.layer.ILayer;
import org.nd4j.linalg.api.ndarray.INDArray;

public class ConsoleTestingApp {

    public static void main(String[] args) {
        INeuralNetwork network = new SimpleNeuralNetworkSpecimen("bestInGen10.txt");
        printNetwork(network);
        IGame<ConsolePDPlayer, AIPDPlayer> testGame = new PDGameLogs<>(new ConsolePDPlayer(), new AIPDPlayer(network), 12);
        testGame.run();
    }

    private static void printNetwork(INeuralNetwork network) {
        for (ILayer layer: network.getLayers()) {
            for (INDArray parameter: layer.getParameters()) {
                System.out.println(parameter);
            }
        }
    }
}
