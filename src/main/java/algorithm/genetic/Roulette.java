package algorithm.genetic;

import domain.Population;
import domain.Solution;
import lombok.Getter;

import static utils.RandomUtils.randomNumber;

public class Roulette implements Selection {
    @Getter
    private double[] roulette;
    private int populationSize;

    public Roulette(int populationSize) {
        this.populationSize = populationSize;
        roulette = new double[populationSize];
    }

    @Override
    public Solution select(Population population) {
        Solution[] solutions = population.getSolutions();

        fillRoulette(solutions);

        double number = randomNumber(0, roulette[populationSize - 1]);

        for (int i = 0; i < solutions.length; i++) {
            if (number < roulette[i]) {
                return solutions[i];
            }
        }

        return null;
    }

    private void fillRoulette(Solution[] solutions) {
        roulette[0] = 1.0 / (double) (solutions[0].getCost());

        for (int i = 1; i < solutions.length; i++) {
            roulette[i] = 1.0 / (double) (solutions[i].getCost()) + roulette[i - 1];
        }
    }

}
