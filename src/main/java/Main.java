import algorithm.genetic.*;
import algorithm.greedy.Greedy;
import algorithm.randomSearch.RandomSearch;
import domain.Evaluator;
import domain.Solution;
import domain.Validator;
import utils.CsvLogger;
import utils.MatrixReader;

import java.net.URL;

public class Main {

    public static void main(String[] args) {
        run(20);
    }

    public static void run(int problemSize) {
        URL url = Main.class.getResource("had" + problemSize + ".dat.txt");

        MatrixReader matrixReader = new MatrixReader(url);
        matrixReader.read();

        Evaluator evaluator = new Evaluator(matrixReader.getDistanceMatrix(), matrixReader.getFlowMatrix());
        Validator validator = new Validator();

        Greedy greedy = new Greedy(problemSize, evaluator, validator);
        Solution solution = greedy.findSolution();
        System.out.println(solution);

//        System.out.println("NUMBER OF DISTINCT VALUES: LOCATION: " + ArrayUtils.getDistinctValuesQuantityOfArray(solution.getLocations()));
//        System.out.println("NUMBER OF DISTINCT VALUES: FACTORIES: " + ArrayUtils.getDistinctValuesQuantityOfArray(solution.getFactories()));

        CsvLogger randomSearchLogger = new CsvLogger("randomSearch" + problemSize + ".csv");
        RandomSearch randomSearch = new RandomSearch(1000, 12, 10, evaluator, randomSearchLogger);
        randomSearch.run();

        CsvLogger geneticLogger = new CsvLogger("genetic" + problemSize + ".csv");
        Genetic genetic = Genetic.builder()
                .selection(new Roulette(100))
                .crossover(new Crossover(20))
                .stopCondition(new StopAfterIterationsFinishedCondition())
                .mutation(new Mutation(10))
                .iterationsQuantity(200)
                .populationSize(100)
                .problemSize(problemSize)
                .evaluator(evaluator)
                .logger(geneticLogger)
                .build();

        genetic.run();
    }
}
