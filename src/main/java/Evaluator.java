public class Evaluator {
    private int[][] flowMatrix;
    private int[][] distanceMatrix;

    public Evaluator(int[][] flowMatrix, int[][] distanceMatrix) {
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
    }

    public int getCost(Assignment assignment) {
        return getCost(assignment.getLocations(), assignment.getFactories());
    }

    public int getCost(int[] locations, int[] factories) {
        int cost = 0;
        for (int i = 0; i < locations.length - 1; i++) {
            cost += distanceMatrix[locations[i]][locations[i + 1]] *
                    flowMatrix[factories[i]][factories[i + 1]];
        }
        return cost;
    }

    public int getCost(int first, int second, int[] locations, int[] factories){
        return distanceMatrix[locations[first]][locations[second]] *
                flowMatrix[factories[first]][factories[second]];
    }
}
