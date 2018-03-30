package algorithm.randomSearch;

import static utils.RandomUtils.randomSolutions;

import domain.Evaluator;
import domain.Population;
import utils.Logger;

public class RandomSearch {
    private int populationSize;
    private int problemSize;
    private int iterationsQuantity;
    private Evaluator evaluator;
    private Logger logger;

    public RandomSearch(int populationSize, int problemSize, int iterationsQuantity, Evaluator evaluator, Logger logger) {
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
        logger.printStrongAvgWeak(0, population);

        for (int i = 1; i < iterationsQuantity; i++) {
            randomSolutions(population.getSolutions());
            population.calculateCostsAndSearchForStrongAvgWeak();
            logger.printStrongAvgWeak(i, population);
        }

        logger.finishRun();
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
