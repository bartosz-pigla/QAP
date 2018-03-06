package domain;

import lombok.Getter;
import lombok.Setter;

public class Population {
    @Getter
    @Setter
    private Solution[] solutions;

    @Getter
    private int avgCost;

    @Getter
    private Solution strong;

    @Getter
    private Solution weak;

    private Evaluator evaluator;

    public Population(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Population(Solution[] solutions, Evaluator evaluator) {
        this.solutions = solutions;
        this.evaluator = evaluator;
    }

    public void calculateCostsAndSearchForStrongAvgWeak() {
        int strongCost = Integer.MAX_VALUE;
        int weakCost = Integer.MIN_VALUE;
        avgCost = 0;

        Solution current = null;
        int currentCost;

        for (int i = 0; i < solutions.length; i++) {
            current = solutions[i];
            currentCost = evaluator.getCost(current);
            current.setCost(currentCost);
            avgCost += currentCost;
            if (currentCost < strongCost) {
                strongCost = currentCost;
                strong = current;
                strong.setCost(currentCost);
            }
            if (currentCost > weakCost) {
                weakCost = currentCost;
                weak = current;
                weak.setCost(currentCost);
            }
        }

        avgCost = avgCost / solutions.length;
    }

    public void searchForStrongAvgWeak() {
        int strongCost = Integer.MAX_VALUE;
        int weakCost = Integer.MIN_VALUE;
        avgCost = 0;

        Solution current = null;
        int currentCost;

        for (int i = 0; i < solutions.length; i++) {
            current = solutions[i];
            currentCost = current.getCost();
            avgCost += currentCost;
            if (currentCost < strongCost) {
                strongCost = currentCost;
                strong = current;
            }
            if (currentCost > weakCost) {
                weakCost = currentCost;
                weak = current;
            }
        }

        avgCost = avgCost / solutions.length;
    }

}
