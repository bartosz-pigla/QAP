package algorithm.genetic;

import domain.Population;
import domain.Solution;

public class StopWhenManySolutionSimilarToStrong implements StopCondition {

    private double similarity;
    private int similarSolutionsCount;

    private int costLimit;

    public StopWhenManySolutionSimilarToStrong(double similarity, int similarSolutionsCount) {
        this.similarity = similarity;
        this.similarSolutionsCount = similarSolutionsCount;
    }

    @Override
    public boolean isNotFinished(Genetic genetic, Population population, int populationIdx) {
        int strongCost = population.getStrong().getCost();
        calculateLimits(strongCost);
        int currentCost;
        int solutionsCount = 0;
        for (Solution solution : population.getSolutions()) {
            currentCost = solution.getCost();
            if (currentCost <= costLimit) {
                solutionsCount++;
            }
        }

        return solutionsCount < similarSolutionsCount;
    }

    private void calculateLimits(int strongCost) {
        costLimit = (int) ((1 + similarity) * strongCost);
    }
}
