package algorithm.genetic;

import static utils.RandomUtils.isSelected;
import static utils.RandomUtils.randomSolutions;
import static utils.RandomUtils.selectSolutionOtherThan;

import java.util.List;

import com.google.common.collect.ImmutableList;
import domain.Evaluator;
import domain.Population;
import domain.Solution;
import lombok.Builder;
import lombok.Value;
import utils.Logger;

@Builder
@Value
public class Genetic {

    int problemSize;

    int populationSize;
    int iterationsQuantity;

    StopCondition stopCondition;

    Mutation mutation;
    Crossover crossover;

    Selection selection;
    Evaluator evaluator;

    Logger logger;

    public void run() {
        logger.printAlgorithmInfo(this);

        Population oldPopulation = new Population(randomSolutions(problemSize, populationSize), evaluator);
        oldPopulation.calculateCostsAndSearchForStrongAvgWeak();
        logger.printStrongAvgWeak(0, oldPopulation);

        Population newPopulation = null;
        for (int i = 1; stopCondition.isNotFinished(this, oldPopulation, i); i++) {
            newPopulation = generateNewPopulation(oldPopulation);
            newPopulation.searchForStrongAvgWeak();
            logger.printStrongAvgWeak(i, newPopulation);
            oldPopulation = newPopulation;
        }
        logger.finishRun();
    }

    private Population generateNewPopulation(Population oldPopulation) {
        Population newPopulation = new Population(new Solution[oldPopulation.getSolutions().length], evaluator);
        newPopulation.getSolutions()[0] = Solution.copyOf(oldPopulation.getStrong());
        Solution current = null;
        for (int i = 1; i < populationSize; i++) {
            current = selection.select(oldPopulation);
            newPopulation.getSolutions()[i] = current;

            List<Solution> crossedSolutions = getCrossedSolutions(current, oldPopulation);
            if (!crossedSolutions.isEmpty()) {
                newPopulation.getSolutions()[i] = crossedSolutions.get(0);
                newPopulation.getSolutions()[i].setCost(evaluator.getCost(newPopulation.getSolutions()[i]));
                if (i < populationSize - 1) {
                    newPopulation.getSolutions()[i + 1] = crossedSolutions.get(1);
                    newPopulation.getSolutions()[i + 1].setCost(evaluator.getCost(newPopulation.getSolutions()[i + 1]));
                    i++;
                }
            }

            Solution mutatedSolution = getMutatedSolution(current);
            if (mutatedSolution != null) {
                newPopulation.getSolutions()[i] = mutatedSolution;
                newPopulation.getSolutions()[i].setCost(evaluator.getCost(newPopulation.getSolutions()[i]));
            }
        }
        return newPopulation;
    }

    private List<Solution> getCrossedSolutions(Solution solution, Population oldPopulation) {
        if (isSelected(crossover.getProbability())) {
            Solution copyOfFirst = Solution.copyOf(solution);
            Solution copyOfSecond = Solution.copyOf(selectSolutionOtherThan(solution, oldPopulation.getSolutions()));
            crossover.doCrossover(copyOfFirst, copyOfSecond);
            return ImmutableList.of(copyOfFirst, copyOfSecond);
        } else {
            return ImmutableList.of();
        }
    }

    private Solution getMutatedSolution(Solution solution) {
        if (isSelected(mutation.getProbability())) {
            Solution copyOfSolution = Solution.copyOf(solution);
            mutation.doMutation(copyOfSolution);
            return copyOfSolution;
        } else {
            return null;
        }
    }

    private Solution mutateAndCrossoverTheBest(Solution solution, Population oldPopulation) {
        List<Solution> crossedSolutions = getCrossedSolutions(solution, oldPopulation);
        if (!crossedSolutions.isEmpty()) {
            solution = crossedSolutions.get(0);
        }

        Solution mutatedSolution = getMutatedSolution(solution);
        if (mutatedSolution != null) {
            solution = mutatedSolution;
        }
        return solution;
    }

}
