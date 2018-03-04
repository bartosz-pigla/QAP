package algorithm.genetic;

import domain.Population;
import domain.Solution;
import lombok.Value;

import static utils.RandomUtils.randomNumber;

@Value
public class Tournament implements Selection {
    int tournamentSize;

    @Override
    public Solution select(Population population) {
        Solution winner = null;
        int winnerCost = Integer.MAX_VALUE;
        int currentIdx;
        Solution currentSolution = null;
        for (int i = 0; i < tournamentSize; i++) {
            currentIdx = randomNumber(0, population.getSolutions().length - 1);
            currentSolution = population.getSolutions()[currentIdx];
            if (currentSolution.getCost() < winnerCost) {
                winnerCost = currentSolution.getCost();
                winner = currentSolution;
            }
        }
        return winner;
    }
}
