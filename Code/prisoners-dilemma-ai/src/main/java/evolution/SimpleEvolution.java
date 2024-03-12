package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.ISpecimenFactory;

import java.util.ArrayList;
import java.util.List;

public class SimpleEvolution implements IEvolution {
    private double smallMutationChance;
    private int smallMutationAmplitude;
    private double bigMutationChance;
    private int bigMutationAmplitude;
    private boolean oneParent;
    private int currentGenerationIndex;
    private int generationSize;
    private List<ISpecimen> currentGeneration;
    private List<ISpecimen> nextGeneration;

    public SimpleEvolution(double smallMutationChance, int smallMutationAmplitude, double bigMutationChance, int bigMutationAmplitude, boolean oneParent, int generationSize, ISpecimenFactory factory) {
        this.smallMutationChance = smallMutationChance;
        this.smallMutationAmplitude = smallMutationAmplitude;
        this.bigMutationChance = bigMutationChance;
        this.bigMutationAmplitude = bigMutationAmplitude;
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
        return null;
    }

    @Override
    public ISpecimen getWorstSpecimen() {
        return null;
    }

    @Override
    public ISpecimen getMedianSpecimen() {
        return null;
    }

    @Override
    public void generateNextGeneration() {

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
    public int getSmallMutationAmplitude() {
        return smallMutationAmplitude;
    }

    @Override
    public void setSmallMutationAmplitude(int smallMutationAmplitude) {
        this.smallMutationAmplitude = smallMutationAmplitude;
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
    public int getBigMutationAmplitude() {
        return bigMutationAmplitude;
    }

    @Override
    public void setBigMutationAmplitude(int bigMutationAmplitude) {
        this.bigMutationAmplitude = bigMutationAmplitude;
    }
}
