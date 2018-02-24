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
        population.searchForStrongAvgWeak();
        logger.printStrongAvgWeak(1,population);

        for (int i = 1; i < iterationsQuantity; i++) {
            randomSolutions(population.getSolutions());
            population.searchForStrongAvgWeak();
            logger.printStrongAvgWeak(i+1,population);
        }

        logger.finish();
    }

    private void randomSolutions(Solution[] solutions) {
        for (int i = 0; i < solutions.length; i++) {
            Utils.shuffleArray(solutions[i].getLocations());
            Utils.shuffleArray(solutions[i].getFactories());
        }
    }

    private Solution[] randomSolutions(int problemSize, int populationSize) {
        Solution[] solutions = new Solution[populationSize];
        int[] sortedArray = Utils.getSortedArray(problemSize);
        int[] distance, factories;
        for (int i = 0; i < populationSize; i++) {
            distance = sortedArray.clone();
            Utils.shuffleArray(distance);
            factories = sortedArray.clone();
            Utils.shuffleArray(factories);
            solutions[i] = new Solution(distance, factories);
        }
        return solutions;
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
