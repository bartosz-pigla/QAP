package algorithm.genetic;

import domain.Evaluator;
import domain.Population;
import domain.Solution;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static utils.RandomUtils.*;

@Builder
@Value
@AllArgsConstructor
public class Genetic {
    int problemSize;

    int populationSize;
    int iterationsQuantity;

    Mutation mutation;
    Crossover crossover;

    Selection selection;
    Evaluator evaluator;

    public void run() {
        Population population = new Population(randomSolutions(problemSize, populationSize), evaluator);

    }

    private Population generateNewPopulation(Population oldPopulation) {
        Population newPopulation = new Population(new Solution[oldPopulation.getSolutions().length],evaluator);
        Solution current = null;
        for (int i = 0; i < populationSize; i++) {
            current = selection.select(oldPopulation);
            tryToDoCrossover(current, oldPopulation, newPopulation);
            tryToDoMutation(current);
        }
    }

    private void tryToDoCrossover(Solution solution, Population oldPopulation, Population newPopulation){
        if (isSelected(crossover.getProbability())) {
            Solution copyOfFirst = Solution.copyOf(solution);
            Solution copyOfSecond = Solution.copyOf(selectSolutionOtherThan(solution, oldPopulation.getSolutions()));
            crossover.doCrossover(copyOfFirst, copyOfSecond);


        }
    }

    private void tryToDoMutation(Solution solution){
        if (isSelected(mutation.getProbability())){

        }
    }
}
