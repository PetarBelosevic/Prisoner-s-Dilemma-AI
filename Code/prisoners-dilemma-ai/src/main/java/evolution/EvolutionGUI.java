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
    private final DefaultListModel<Integer> bestList;
    private final DefaultListModel<Integer> medianList;
    private final DefaultListModel<Integer> worstList;

    public EvolutionGUI(IEvolution<T> evolution, int step, DefaultListModel<Integer> indexList, DefaultListModel<Integer> bestList, DefaultListModel<Integer> medianList, DefaultListModel<Integer> worstList) {
        super(evolution);
        this.step = step;
        this.indexList = indexList;
        this.bestList = bestList;
        this.medianList = medianList;
        this.worstList = worstList;
    }

    public EvolutionGUI(IEvolution<T> evolution, DefaultListModel<Integer> indexList, DefaultListModel<Integer> bestList, DefaultListModel<Integer> medianList, DefaultListModel<Integer> worstList) {
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
