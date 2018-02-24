public class Population {
    private Assignment[] assignments;
    private int avgCost;
    private Assignment strong;
    private Assignment weak;
    private int strongCost;
    private int weakCost;
    private Evaluator evaluator;

    public Population(Assignment[] assignments, Evaluator evaluator) {
        this.assignments = assignments;
        this.evaluator = evaluator;
    }


    public void searchForStrongAvgWeak() {
        strongCost = Integer.MAX_VALUE;
        weakCost = Integer.MIN_VALUE;

        Assignment current = null;
        int currentCost;

        for (int i = 0; i < assignments.length; i++) {
            current = assignments[i];
            currentCost = evaluator.getCost(current);
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

        avgCost = avgCost / assignments.length;
    }

    public int getAvgCost() {
        return avgCost;
    }

    public Assignment getStrong() {
        return strong;
    }

    public Assignment getWeak() {
        return weak;
    }

    public int getStrongCost() {
        return strongCost;
    }

    public int getWeakCost() {
        return weakCost;
    }

    public Assignment[] getAssignments() {
        return assignments;
    }
}
