public abstract class Algorithm {
    protected int[][]flowMatrix;
    protected int[][]distanceMatrix;
    protected int problemSize;

    public Algorithm(int[][] flowMatrix, int[][] distanceMatrix, int problemSize) {
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
        this.problemSize = problemSize;
    }

    public abstract Assignment findSolution();
}
