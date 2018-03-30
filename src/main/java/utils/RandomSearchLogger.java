package utils;

import java.io.FileWriter;
import java.io.IOException;

import algorithm.randomSearch.RandomSearch;
import au.com.bytecode.opencsv.CSVWriter;
import domain.Population;

public class RandomSearchLogger implements Logger<RandomSearch> {

    private CSVWriter writer;
    private int currentRun;
    private int iterationsQuantity;
    private int[][] statistics;

    public RandomSearchLogger(String filename, int iterationsQuantity) {
        try {
            this.writer = new CSVWriter(new FileWriter(filename), '\t');
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        this.iterationsQuantity = iterationsQuantity;
        this.statistics = new int[iterationsQuantity][];
        for (int i = 0; i < iterationsQuantity; i++) {
            this.statistics[i] = new int[3];
        }
    }

    public void printAlgorithmInfo(RandomSearch randomSearch) {
        if(currentRun==0){
            printAlgorithmInfoHelper(randomSearch);
        }


    }

    private void printAlgorithmInfoHelper(RandomSearch randomSearch){
        writer.writeNext(new String[]{
                "populationSize",
                "iterationsQuantity",
                "problemSize"
        });

        writer.writeNext(new String[]{
                String.valueOf(randomSearch.getPopulationSize()),
                String.valueOf(randomSearch.getIterationsQuantity()),
                String.valueOf(randomSearch.getProblemSize())
        });

        writer.writeNext(new String[]{
                "populationNumber",
                "strong",
                "avg",
                "weak"
        });
    }

    public void printStrongAvgWeak(int iterationNumber, Population population) {
        statistics[iterationNumber][0] =
                (statistics[iterationNumber][0] * currentRun + population.getStrong().getCost()) / (currentRun + 1);

        statistics[iterationNumber][1] =
                (statistics[iterationNumber][1] * currentRun + population.getAvgCost()) / (currentRun + 1);

        statistics[iterationNumber][2] =
                (statistics[iterationNumber][2] * currentRun + population.getWeak().getCost()) / (currentRun + 1);
    }

    public void finishRun() {
        currentRun++;
    }

    public void finish() {
        printStatistics();
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printStatistics() {
        String[] row = new String[4];
        for (int iterationNumber = 0; iterationNumber < iterationsQuantity; iterationNumber++) {
            row[0] = String.valueOf(iterationNumber);
            row[1] = String.valueOf(statistics[iterationNumber][0]);
            row[2] = String.valueOf(statistics[iterationNumber][1]);
            row[3] = String.valueOf(statistics[iterationNumber][2]);
            writer.writeNext(row);
        }
    }

}
