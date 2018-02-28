package algorithm.genetic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class Genetic {
    int problemSize;

    int populationSize;
    int iterationsQuantity;

    Mutation mutation;
    Crossover crossover;

    Selection selection;

    public void run() {
        for (int i = 0; i < populationSize; i++) {

        }
    }
}
