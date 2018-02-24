public class Evaluator {
    private int[][] distanceMatrix;
    private int[][] flowMatrix;


    public Evaluator(int[][] distanceMatrix, int[][] flowMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.flowMatrix = flowMatrix;
    }

    public int getCost(Solution solution) {
        return getCost(solution.getLocations(), solution.getFactories());
    }

    public int getCost(int[] locations, int[] factories) {
        int cost = 0;
        for (int i = 0; i < locations.length - 1; i++) {
            cost += distanceMatrix[locations[i]][locations[i + 1]] *
                    flowMatrix[factories[i]][factories[i + 1]];
        }
        return cost;
    }

    public int getCost(int first, int second, int[] locations, int[] factories) {
        return distanceMatrix[locations[first]][locations[second]] *
                flowMatrix[factories[first]][factories[second]];
    }
}
