public class Population {
    private Solution[] solutions;
    private int avgCost;
    private Solution strong;
    private Solution weak;
    private int strongCost;
    private int weakCost;
    private Evaluator evaluator;

    public Population(Solution[] solutions, Evaluator evaluator) {
        this.solutions = solutions;
        this.evaluator = evaluator;
    }


    public void searchForStrongAvgWeak() {
        strongCost = Integer.MAX_VALUE;
        weakCost = Integer.MIN_VALUE;

        Solution current = null;
        int currentCost;

        for (int i = 0; i < solutions.length; i++) {
            current = solutions[i];
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

        avgCost = avgCost / solutions.length;
    }

    public int getAvgCost() {
        return avgCost;
    }

    public Solution getStrong() {
        return strong;
    }

    public Solution getWeak() {
        return weak;
    }

    public int getStrongCost() {
        return strongCost;
    }

    public int getWeakCost() {
        return weakCost;
    }

    public Solution[] getSolutions() {
        return solutions;
    }
}
