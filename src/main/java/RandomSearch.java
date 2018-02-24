public class RandomSearch {
    private int populationSize;
    private int problemSize;
    private int iterationsQuantity;
    private Evaluator evaluator;

    public RandomSearch(int populationSize, int problemSize, int iterationsQuantity, Evaluator evaluator) {
        this.populationSize = populationSize;
        this.problemSize = problemSize;
        this.iterationsQuantity = iterationsQuantity;
        this.evaluator = evaluator;
    }

    public void run() {
        Population population = new Population(randomSolutions(problemSize, populationSize), evaluator);

        for (int i = 1; i < iterationsQuantity; i++) {
            randomSolutions(population.getSolutions());
            population.searchForStrongAvgWeak();
        }
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
}
