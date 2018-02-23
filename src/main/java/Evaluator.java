public class Evaluator {
    private int[][] flowMatrix;
    private int[][] distanceMatrix;

    public Evaluator(int[][] flowMatrix, int[][] distanceMatrix) {
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
    }

    public int getCost(int[] permutation) {
        int cost = 0;
        for (int i = 0; i < permutation.length-1; i++) {
            cost += distanceMatrix[i][i + 1] *
                    flowMatrix[permutation[i]][permutation[i + 1]];
        }
        return cost;
    }

    private int getFlow(int firstFlow, int lastFlow){

    }
}
