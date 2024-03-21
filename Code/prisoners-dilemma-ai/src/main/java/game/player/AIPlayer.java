package game.player;

import neuralNetwork.INeuralNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

public class AIPlayer extends AbstractPlayer {
    private INeuralNetwork neuralNetwork;
    private double[] inputArray;

    public AIPlayer(INeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
        this.inputArray = new double[neuralNetwork.getLayer(0).getRowNumber()];
    }

    @Override
    public boolean getDecision(List<Integer> otherDecisionHistory) {
        // TODO: saljem samo povijest drugog igraca ili i svoju povijest?
        // za sad samo drugi igrac
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = i < otherDecisionHistory.size() ? otherDecisionHistory.get(i) : 0;
        }

        double[] result = neuralNetwork.process(inputArray);
        boolean decision = result[0] >= 0.5;

        decisionHistory.add(decision ? 1 : -1);
        return decision;
    }
}
