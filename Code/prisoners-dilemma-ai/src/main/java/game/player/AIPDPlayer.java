package game.player;

import neuralNetwork.INeuralNetwork;

import java.util.List;

/**
 * <p>
 *     Player for Prisoner's Dilemma game that makes binary decisions using neural network.
 * </p>
 */
public class AIPDPlayer extends AbstractPlayer {
    private INeuralNetwork neuralNetwork;
    private double[] inputArray;

    public AIPDPlayer(INeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
        this.inputArray = new double[neuralNetwork.getLayer(0).getNumberOfInputs()];
    }

    public AIPDPlayer() {}

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int n = otherDecisionHistory.size() - 1;
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = i <= n ? otherDecisionHistory.get(n - i) : 0;
        }

        double[] result = neuralNetwork.process(inputArray);
        boolean decision = result[0] >= 0.5;

        getDecisionHistory().add(decision ? 1 : -1);
        return decision ? 1 : -1;
    }

    /**
     * @return currently used neural network
     */
    public INeuralNetwork getNeuralNetwork() {
        return this.neuralNetwork;
    }

    /**
     * @param network new network that this player will be using
     */
    public void setNeuralNetwork(INeuralNetwork network) {
        this.neuralNetwork = network;
        if (inputArray == null || inputArray.length != neuralNetwork.getLayer(0).getNumberOfInputs()) {
            this.inputArray = new double[neuralNetwork.getLayer(0).getNumberOfInputs()];
        }
    }

    @Override
    public void reset() {
        super.reset();
        neuralNetwork.clearNetwork();
    }
}
