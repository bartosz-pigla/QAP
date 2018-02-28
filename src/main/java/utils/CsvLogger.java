package utils;

import algorithm.randomSearch.RandomSearch;
import au.com.bytecode.opencsv.CSVWriter;
import domain.Population;

import java.io.FileWriter;
import java.io.IOException;

public class CsvLogger {
    private CSVWriter writer;

    public CsvLogger(String filename) {
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

    public void printStrongAvgWeak(int populationNumber, Population population) {
        String[] row = new String[]{
                String.valueOf(populationNumber),
                String.valueOf(population.getStrongCost()),
                String.valueOf(population.getAvgCost()),
                String.valueOf(population.getWeakCost()),
        };
        writer.writeNext(row);
    }

    public void finish() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
