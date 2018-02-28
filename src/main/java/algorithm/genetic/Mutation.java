package algorithm.genetic;

import domain.Solution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
public class Mutation {
    @Getter
    int probability;

    public void doMutation(Solution solution) {

    }
}
