package algorithm.genetic;

import domain.Solution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import static utils.ArrayUtils.swap;
import static utils.RandomUtils.randomNumber;

@Value
@AllArgsConstructor
public class Mutation {
    @Getter
    int probability;

    public void doMutation(Solution solution) {
        if (Math.random() < 0.5) {
            mutateArray(solution.getLocations());
        } else {
            mutateArray(solution.getFactories());
        }
    }

    private void mutateArray(int[] array) {
        int firstElement = randomNumber(0, array.length);
        int secondElement = randomNumber(0, array.length);
        swap(array, firstElement, secondElement);
    }

}
