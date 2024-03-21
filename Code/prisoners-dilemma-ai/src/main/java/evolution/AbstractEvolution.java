package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.ISpecimenFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEvolution implements IEvolution {
    protected double smallMutationChance;
    protected int smallMutationMagnitude;
    protected double bigMutationChance;
    protected int bigMutationMagnitude;
    protected boolean oneParent;
    protected int currentGenerationIndex = 0;
    protected int generationSize;
    protected List<ISpecimen> currentGeneration;
    protected List<ISpecimen> nextGeneration;

    protected AbstractEvolution(double smallMutationChance, int smallMutationAmplitude, double bigMutationChance, int bigMutationAmplitude, boolean oneParent, int generationSize, ISpecimenFactory factory) {
        this.smallMutationChance = smallMutationChance;
        this.smallMutationMagnitude = smallMutationAmplitude;
        this.bigMutationChance = bigMutationChance;
        this.bigMutationMagnitude = bigMutationAmplitude;
        this.oneParent = oneParent;
        this.generationSize = generationSize;
        initialize(factory);
    }

    private void initialize(ISpecimenFactory factory) {
        currentGeneration = new ArrayList<>(generationSize);
        nextGeneration = new ArrayList<>(generationSize);

        for (int i = 0; i < generationSize; i++) {
            currentGeneration.add(factory.create());
        }
    }

    @Override
    public ISpecimen getBestSpecimen() {
        return currentGeneration.get(0);
    }

    @Override
    public ISpecimen getWorstSpecimen() {
        return currentGeneration.get(generationSize - 1);
    }

    @Override
    public ISpecimen getMedianSpecimen() {
        return currentGeneration.get(generationSize / 2);
    }

    @Override
    public int getCurrentGenerationIndex() {
        return currentGenerationIndex;
    }

    @Override
    public void setOneParent(boolean oneParent) {
        this.oneParent = oneParent;
    }

    @Override
    public boolean isOneParent() {
        return oneParent;
    }

    @Override
    public double getSmallMutationChance() {
        return smallMutationChance;
    }

    @Override
    public void setSmallMutationChance(double smallMutationChance) {
        this.smallMutationChance = smallMutationChance;
    }

    @Override
    public int getSmallMutationMagnitude() {
        return smallMutationMagnitude;
    }

    @Override
    public void setSmallMutationMagnitude(int smallMutationMagnitude) {
        this.smallMutationMagnitude = smallMutationMagnitude;
    }

    @Override
    public double getBigMutationChance() {
        return bigMutationChance;
    }

    @Override
    public void setBigMutationChance(double bigMutationChance) {
        this.bigMutationChance = bigMutationChance;
    }

    @Override
    public int getBigMutationMagnitude() {
        return bigMutationMagnitude;
    }

    @Override
    public void setBigMutationMagnitude(int bigMutationMagnitude) {
        this.bigMutationMagnitude = bigMutationMagnitude;
    }

    @Override
    public int getGenerationSize() {
        return generationSize;
    }
}
