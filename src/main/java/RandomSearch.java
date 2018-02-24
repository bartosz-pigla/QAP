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
        Population population = new Population(randomAssignments(problemSize, populationSize), evaluator);

        for (int i = 1; i < iterationsQuantity; i++) {
            randomAssignments(population.getAssignments());
            population.searchForStrongAvgWeak();
        }
    }

    private void randomAssignments(Assignment[] assignments) {
        for (int i = 0; i < assignments.length; i++) {
            Utils.shuffleArray(assignments[i].getLocations());
            Utils.shuffleArray(assignments[i].getFactories());
        }
    }

    private Assignment[] randomAssignments(int problemSize, int populationSize) {
        Assignment[] assignments = new Assignment[populationSize];
        int[] sortedArray = Utils.getSortedArray(problemSize);
        int[] distance, factories;
        for (int i = 0; i < populationSize; i++) {
            distance = sortedArray.clone();
            Utils.shuffleArray(distance);
            factories = sortedArray.clone();
            Utils.shuffleArray(factories);
            assignments[i] = new Assignment(distance, factories);
        }
        return assignments;
    }
}
