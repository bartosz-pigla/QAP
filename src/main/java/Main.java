import algorithm.genetic.*;
import algorithm.greedy.Greedy;
import algorithm.randomSearch.RandomSearch;
import domain.Evaluator;
import domain.Solution;
import domain.Validator;
import utils.GeneticLogger;
import utils.Logger;
import utils.MatrixReader;
import utils.RandomSearchLogger;

import java.net.URL;

public class Main {

    public static void main(String[] args) {
        //runGeneticAlgorithm(20);
        runGeneticAlgorithm(20,10);
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

        Logger randomSearchLogger = new RandomSearchLogger("randomSearch" + problemSize + ".csv");
        RandomSearch randomSearch = new RandomSearch(1000, 12, 10, evaluator, randomSearchLogger);
        randomSearch.run();
    }

    public static void runGeneticAlgorithm(int problemSize, int runQuantity){
        URL url = Main.class.getResource("had" + problemSize + ".dat.txt");

        MatrixReader matrixReader = new MatrixReader(url);
        matrixReader.read();
        Evaluator evaluator = new Evaluator(matrixReader.getDistanceMatrix(), matrixReader.getFlowMatrix());
        int iterationsQuantity = 200;
        int populationSize = 100;
        GeneticLogger geneticLogger = new GeneticLogger("genetic" + problemSize + ".csv", iterationsQuantity);
        Genetic genetic = Genetic.builder()
                .selection(new Roulette(populationSize))
                .crossover(new Crossover(20))
                .stopCondition(new IterationsFinishedCondition())
                .mutation(new Mutation(10))
                .iterationsQuantity(iterationsQuantity)
                .populationSize(populationSize)
                .problemSize(problemSize)
                .evaluator(evaluator)
                .logger(geneticLogger)
                .build();

        for(int i=0; i<runQuantity;i++){
            genetic.run();
        }

        geneticLogger.finish();

    }
}
