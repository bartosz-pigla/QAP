package utils;

import domain.Solution;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static int randomNumber(int min, int max) {
        return random.nextInt(0, max);
    }

    public static double randomNumber(double min, double max) {
        return random.nextDouble(0, max);
    }

    public static boolean isSelected(int probability) {
        return random.nextInt(0, 100) < probability;
    }

    public static Solution selectSolutionOtherThan(Solution solutionToExclude, Solution[] solutions) {
        int idx;
        do {
            idx = random.nextInt(0, solutions.length);
        } while (solutions[idx] == solutionToExclude);

        return solutions[idx];
    }

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
