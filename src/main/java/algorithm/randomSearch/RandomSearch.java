package algorithm.randomSearch;

import domain.Evaluator;
import domain.Population;
import utils.CsvLogger;

import static utils.RandomUtils.randomSolutions;

public class RandomSearch {
    private int populationSize;
    private int problemSize;
    private int iterationsQuantity;
    private Evaluator evaluator;
    private CsvLogger logger;

    public RandomSearch(int populationSize, int problemSize, int iterationsQuantity, Evaluator evaluator, CsvLogger logger) {
        this.populationSize = populationSize;
        this.problemSize = problemSize;
        this.iterationsQuantity = iterationsQuantity;
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void run() {
        logger.printAlgorithmInfo(this);

        Population population = new Population(randomSolutions(problemSize, populationSize), evaluator);
        population.calculateCostsAndSearchForStrongAvgWeak();
        logger.printStrongAvgWeak(1, population);

        for (int i = 1; i < iterationsQuantity; i++) {
            randomSolutions(population.getSolutions());
            population.calculateCostsAndSearchForStrongAvgWeak();
            logger.printStrongAvgWeak(i + 1, population);
        }

        logger.finish();
    }



    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getProblemSize() {
        return problemSize;
    }

    public void setProblemSize(int problemSize) {
        this.problemSize = problemSize;
    }

    public int getIterationsQuantity() {
        return iterationsQuantity;
    }

    public void setIterationsQuantity(int iterationsQuantity) {
        this.iterationsQuantity = iterationsQuantity;
    }
}
