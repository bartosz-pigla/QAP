import java.net.URL;

import algorithm.genetic.Crossover;
import algorithm.genetic.Genetic;
import algorithm.genetic.Mutation;
import algorithm.genetic.Roulette;
import algorithm.genetic.SimilarToStrongCondition;
import algorithm.greedy.Greedy;
import algorithm.randomSearch.RandomSearch;
import domain.Evaluator;
import domain.Solution;
import domain.Validator;
import utils.GeneticLogger;
import utils.MatrixReader;
import utils.RandomSearchLogger;

public class Main {

    public static void main(String[] args) {
        //run(16);
        runGeneticAlgorithm(16,10);
    }

    public static void run(int problemSize) {
        URL url = Main.class.getResource("had" + problemSize + ".dat.txt");

        MatrixReader matrixReader = new MatrixReader(url);
        matrixReader.read();

        Evaluator evaluator = new Evaluator(matrixReader.getDistanceMatrix(), matrixReader.getFlowMatrix());
        Validator validator = new Validator();

        Greedy greedy = new Greedy(problemSize, evaluator, validator);
        Solution solution = greedy.findSolution();
        solution.setCost(evaluator.getCost(solution));
        System.out.println(solution);

        int runQuantity = 10;
        RandomSearchLogger randomSearchLogger = new RandomSearchLogger("randomSearch" + problemSize + ".csv", 100);
        RandomSearch randomSearch = new RandomSearch(100, problemSize, 100, evaluator, randomSearchLogger);

        for(int i=0; i<runQuantity;i++){
            randomSearch.run();
        }

        randomSearchLogger.finish();
    }

    public static void runGeneticAlgorithm(int problemSize, int runQuantity){
        URL url = Main.class.getResource("had" + problemSize + ".dat.txt");

        MatrixReader matrixReader = new MatrixReader(url);
        matrixReader.read();
        Evaluator evaluator = new Evaluator(matrixReader.getDistanceMatrix(), matrixReader.getFlowMatrix());
        int iterationsQuantity = 100;
        int populationSize = 100;
        GeneticLogger geneticLogger = new GeneticLogger("genetic" + problemSize + ".csv", 10000);
        Genetic genetic = Genetic.builder()
                .selection(new Roulette(populationSize))
                .crossover(new Crossover(20))
                .stopCondition(new SimilarToStrongCondition(0.1, 50))
                .mutation(new Mutation(10))
                .iterationsQuantity(iterationsQuantity)
                .populationSize(populationSize)
                .problemSize(problemSize)
                .evaluator(evaluator)
                .logger(geneticLogger)
                .build();
        genetic.run();

        geneticLogger.finish();

    }
}
