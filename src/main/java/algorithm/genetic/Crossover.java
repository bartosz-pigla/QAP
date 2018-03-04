package algorithm.genetic;

import domain.Solution;
import lombok.Value;

@Value
public class Crossover {
    int probability;

    public void doCrossover(Solution first, Solution second) {

        if (Math.random() < 0.5) {
            swapLocations(first, second);
        } else {
            swapFactories(first, second);
        }

        //swapLocations(first, second);
    }

    private void swapLocations(Solution first, Solution second) {
        int[] locationsTmp = first.getLocations();
        first.setLocations(second.getLocations());
        second.setLocations(locationsTmp);
    }

    private void swapFactories(Solution first, Solution second) {
        int[] factories = first.getLocations();
        first.setLocations(second.getLocations());
        second.setLocations(factories);
    }
}
