package algorithm.genetic;

import domain.Population;

public class IterationsFinishedCondition implements StopCondition{
    @Override
    public boolean isNotFinished(Genetic genetic, Population population, int populationIdx) {
        return populationIdx < genetic.getIterationsQuantity();
    }
}
