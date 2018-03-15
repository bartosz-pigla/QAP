package utils;

import algorithm.randomSearch.RandomSearch;
import au.com.bytecode.opencsv.CSVWriter;
import domain.Population;

import java.io.FileWriter;
import java.io.IOException;

public class RandomSearchLogger implements Logger<RandomSearch> {

    private CSVWriter writer;

    public RandomSearchLogger(String filename) {
        try {
            this.writer = new CSVWriter(new FileWriter(filename), '\t');
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void printAlgorithmInfo(RandomSearch randomSearch) {
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
        String[] row = new String[]{
                String.valueOf(iterationNumber),
                String.valueOf(population.getStrong().getCost()),
                String.valueOf(population.getAvgCost()),
                String.valueOf(population.getWeak().getCost()),
        };
        writer.writeNext(row);
    }

    public void finishRun() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
