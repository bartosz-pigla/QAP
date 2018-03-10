package algorithm.genetic;

import domain.Population;

public class StopAfterIterationsFinishedCondition implements StopCondition{
    @Override
    public boolean isNotFinished(Genetic genetic, Population population, int populationIdx) {
        return populationIdx < genetic.getIterationsQuantity();
    }
}
