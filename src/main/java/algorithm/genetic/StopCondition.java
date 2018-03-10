package algorithm.genetic;

import domain.Population;

public interface StopCondition {
    boolean isNotFinished(Genetic genetic, Population population, int populationIdx);
}
