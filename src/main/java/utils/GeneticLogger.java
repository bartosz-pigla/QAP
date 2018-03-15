package utils;

import algorithm.genetic.Genetic;
import algorithm.genetic.Tournament;
import au.com.bytecode.opencsv.CSVWriter;
import domain.Population;

import java.io.FileWriter;
import java.io.IOException;

public class GeneticLogger implements Logger<Genetic> {

    private CSVWriter writer;
    private int currentRun;
    private int iterationsQuantity;
    private int[][] statistics;

    public GeneticLogger(String filename, int iterationsQuantity) {
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

    public void printAlgorithmInfo(Genetic genetic) {
        writer.writeNext(new String[]{
                "populationSize",
                "iterationsQuantity",
                "problemSize",
                "tournamentSize",
                "crossoverProbability",
                "mutationProbability"
        });

        writer.writeNext(new String[]{
                String.valueOf(genetic.getPopulationSize()),
                String.valueOf(genetic.getIterationsQuantity()),
                String.valueOf(genetic.getProblemSize()),
                (genetic.getSelection() instanceof Tournament) ? String.valueOf(((Tournament) genetic.getSelection()).getTournamentSize()) : "",
                String.valueOf(genetic.getCrossover().getProbability()),
                String.valueOf(genetic.getMutation().getProbability())
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
