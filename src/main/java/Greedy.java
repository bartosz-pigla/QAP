public class Greedy {
    private int[][] distanceMatrix;
    private int[][] flowMatrix;
    private Evaluator evaluator;

    public Greedy(int[][] distanceMatrix, int[][] flowMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.flowMatrix = flowMatrix;
        this.evaluator = new Evaluator(distanceMatrix, flowMatrix);
    }

    public Assignment findSolution() {
        int problemSize = distanceMatrix.length;
        int[] locations = new int[problemSize];
        int[] factories = new int[problemSize];
        locations[0] = 0;
        factories[0] = 0;
        SolutionIndicator indicator = new SolutionIndicator();
        Validator validator = new Validator();

        int i = 1;
        while (i < problemSize) {
            indicator.setCost(Integer.MAX_VALUE);

            for (int locIdx = 0; locIdx < problemSize - 1; locIdx++) {
                for (int facIdx = 0; facIdx < problemSize - 1; facIdx++) {
                    locations[i] = locIdx;
                    factories[i] = facIdx;
                    if (validator.isValid(i, locations, factories)) {
                        int cost = evaluator.getCost(i - 1, i, locations, factories);
                        if (indicator.getCost() > cost) {
                            indicator.setCost(cost);
                            indicator.setLocIdx(locIdx);
                            indicator.setFacIdx(facIdx);
                        }
                    }
                }
            }
            i++;
        }

        return new Assignment(locations, factories);
    }

    static class SolutionIndicator {
        int cost;
        int locIdx;
        int facIdx;

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getLocIdx() {
            return locIdx;
        }

        public void setLocIdx(int locIdx) {
            this.locIdx = locIdx;
        }

        public int getFacIdx() {
            return facIdx;
        }

        public void setFacIdx(int facIdx) {
            this.facIdx = facIdx;
        }
    }
}
