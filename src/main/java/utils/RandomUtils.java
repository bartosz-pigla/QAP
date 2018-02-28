package utils;

import domain.Solution;

public class RandomUtils {
    public static void randomSolutions(Solution[] solutions) {
        for (int i = 0; i < solutions.length; i++) {
            ArrayUtils.shuffleArray(solutions[i].getLocations());
            ArrayUtils.shuffleArray(solutions[i].getFactories());
        }
    }

    public static Solution[] randomSolutions(int problemSize, int populationSize) {
        Solution[] solutions = new Solution[populationSize];
        int[] sortedArray = ArrayUtils.getSortedArray(problemSize);
        int[] distance, factories;
        for (int i = 0; i < populationSize; i++) {
            distance = sortedArray.clone();
            ArrayUtils.shuffleArray(distance);
            factories = sortedArray.clone();
            ArrayUtils.shuffleArray(factories);
            solutions[i] = new Solution(distance, factories);
        }
        return solutions;
    }
}
