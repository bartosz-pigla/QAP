public class Greedy {
    private int[][] distanceMatrix;
    private int[][] flowMatrix;
    private Evaluator evaluator;
    private Validator validator;

    public Greedy(int[][] distanceMatrix, int[][] flowMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.flowMatrix = flowMatrix;
        this.evaluator = new Evaluator(distanceMatrix, flowMatrix);
        this.validator = new Validator();
    }

    public Assignment findSolution() {
        int problemSize = distanceMatrix.length;
        int[] locations = new int[problemSize];
        int[] factories = new int[problemSize];

        doFirstAssignment(locations, factories);

        for (int i = 2; i < problemSize; i++) {
            assignLocation(i, locations, factories);
        }

        return new Assignment(locations, factories);
    }

    public void doFirstAssignment(int[] locations, int[] factories) {
        int firstLoc = 0, secondLoc = 0;
        int firstFac = 0, secondFac = 0;
        int leastCost = Integer.MAX_VALUE;

        for (int i = 0; i < locations.length; i++) {
            locations[0] = i;
            for (int j = 0; j < factories.length; j++) {
                factories[0] = j;
                int cost = assignLocation(1, locations, factories);

                if (validator.isValid(1, locations, factories)) {
                    if (leastCost > cost) {
                        leastCost = cost;
                        firstLoc = locations[0];
                        secondLoc = locations[1];
                        firstFac = factories[0];
                        secondFac = factories[1];
                    }
                }
            }
        }
        locations[0] = firstLoc;
        locations[1] = secondLoc;

        factories[0] = firstFac;
        factories[1] = secondFac;
    }

    public int assignLocation(int idx, int[] locations, int[] factories) {
        SolutionIndicator indicator = new SolutionIndicator();
        indicator.setCost(Integer.MAX_VALUE);

        for (int k = 0; k < locations.length; k++) {
            locations[idx] = k;
            for (int l = 0; l < factories.length; l++) {
                factories[idx] = l;
                if (validator.isValid(idx, locations, factories)) {
                    int cost = evaluator.getCost(idx - 1, idx, locations, factories);
                    if (indicator.getCost() > cost) {
                        indicator.setCost(cost);
                        indicator.setLocIdx(k);
                        indicator.setFacIdx(l);
                    }
                }
            }
        }
        locations[idx] = indicator.getLocIdx();
        factories[idx] = indicator.getFacIdx();
        return indicator.getCost();
    }

//    public Assignment findSolution() {
//        int problemSize = distanceMatrix.length;
//        int[] locations = new int[problemSize];
//        int[] factories = new int[problemSize];
//        locations[0] = 0;
//        factories[0] = 0;
//        SolutionIndicator indicator = new SolutionIndicator();
//        Validator validator = new Validator();
//
//        int i = 1;
//        while (i < problemSize) {
//            indicator.setCost(Integer.MAX_VALUE);
//
//            for (int locIdx = 0; locIdx < problemSize - 1; locIdx++) {
//                for (int facIdx = 0; facIdx < problemSize - 1; facIdx++) {
//                    locations[i] = locIdx;
//                    factories[i] = facIdx;
//                    if (validator.isValid(i, locations, factories)) {
//                        int cost = evaluator.getCost(i - 1, i, locations, factories);
//                        if (indicator.getCost() > cost) {
//                            indicator.setCost(cost);
//                            indicator.setLocIdx(locIdx);
//                            indicator.setFacIdx(facIdx);
//                        }
//                    }
//                }
//            }
//            i++;
//        }
//
//        return new Assignment(locations, factories);
//    }

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
