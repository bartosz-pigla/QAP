public class Main {

    public static void main(String[] args) {
        run(20);
    }

    public static void run(int problemSize) {
        MatrixReader matrixReader = new MatrixReader("had" + problemSize + ".dat.txt", "  ");
        matrixReader.read();

        Evaluator evaluator = new Evaluator(matrixReader.getDistanceMatrix(), matrixReader.getFlowMatrix());
        Validator validator = new Validator();

        Greedy greedy = new Greedy(problemSize, evaluator, validator);
        Solution solution = greedy.findSolution();
        System.out.println(solution);

//        System.out.println("NUMBER OF DISTINCT VALUES: LOCATION: " + Utils.getDistinctValuesQuantityOfArray(solution.getLocations()));
//        System.out.println("NUMBER OF DISTINCT VALUES: FACTORIES: " + Utils.getDistinctValuesQuantityOfArray(solution.getFactories()));

        CsvLogger logger = new CsvLogger("randomSearch" + problemSize + ".csv");
        RandomSearch randomSearch = new RandomSearch(50, 12, 10, evaluator, logger);
        randomSearch.run();
    }
}
