package algorithm;

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

    public void run() {

    }
}
