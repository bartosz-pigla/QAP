package algorithm.genetic;

import domain.Solution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
public class Crossover {
    @Getter
    int probability;

    public void doCrossover(Solution first, Solution second) {
        swapLocations(first,second);
    }

    private void swapLocations(Solution first, Solution second){
        int[] locationsTmp = first.getLocations();
        first.setLocations(second.getLocations());
        second.setLocations(locationsTmp);
    }

    private void swapFactories(Solution first, Solution second){
        int[] factories = first.getLocations();
        first.setLocations(second.getLocations());
        second.setLocations(factories);
    }
}
