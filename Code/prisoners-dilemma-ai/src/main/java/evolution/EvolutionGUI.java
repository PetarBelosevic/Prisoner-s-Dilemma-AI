package evolution;

import evolution.specimen.ISpecimen;

import javax.swing.*;

/**
 * <p>
 *     Evolution decorator that updates list models for showing the best, median and the worst scores in generation.
 * </p>
 * @param <T> type of specimen
 */
public class EvolutionGUI<T extends ISpecimen<T>> extends Evolution<T> {
    private final int step;

    private final DefaultListModel<Integer> indexList;
    private final DefaultListModel<Double> bestList;
    private final DefaultListModel<Double> medianList;
    private final DefaultListModel<Double> worstList;

    public EvolutionGUI(IEvolution<T> evolution, int step, DefaultListModel<Integer> indexList, DefaultListModel<Double> bestList, DefaultListModel<Double> medianList, DefaultListModel<Double> worstList) {
        super(evolution);
        this.step = step;
        this.indexList = indexList;
        this.bestList = bestList;
        this.medianList = medianList;
        this.worstList = worstList;
    }

    public EvolutionGUI(IEvolution<T> evolution, DefaultListModel<Integer> indexList, DefaultListModel<Double> bestList, DefaultListModel<Double> medianList, DefaultListModel<Double> worstList) {
        this(evolution, 10, indexList, bestList, medianList, worstList);
    }

    @Override
    public void generateNextGeneration() {
        evolution.generateNextGeneration();
        if (getCurrentGenerationIndex() % step == 0) {
            indexList.addElement(getCurrentGenerationIndex());
            bestList.addElement(getBestSpecimen().getFitness());
            medianList.addElement(getMedianSpecimen().getFitness());
            worstList.addElement(getWorstSpecimen().getFitness());
        }
    }
}
