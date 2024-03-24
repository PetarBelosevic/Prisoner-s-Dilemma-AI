package evolution.training;

import evolution.specimen.SimpleNeuralNetworkSpecimen;
import game.IGame;
import game.PDGameLogs;
import game.player.AIPDPlayer;
import game.player.ConsolePDPlayer;
import neuralNetwork.INeuralNetwork;

public class TestingConsoleApp {

    public static void main(String[] args) {
        INeuralNetwork network = new SimpleNeuralNetworkSpecimen("bestInGen1.txt");
        IGame<ConsolePDPlayer, AIPDPlayer> testGame = new PDGameLogs<>(new ConsolePDPlayer(), new AIPDPlayer(network), 12);
        testGame.run();
    }
}
