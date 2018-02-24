import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GreedyTest {

    private int[][] distanceMatrix = {
            {0, 9, 5},
            {9, 0, 7},
            {5, 7, 0}
    };

    private int[][] flowMatrix = {
            {0, 6, 4},
            {6, 0, 3},
            {4, 3, 0}
    };

    private Greedy greedy = new Greedy(distanceMatrix, flowMatrix);

    @Test
    void assignLocation() {
        int[] locations = new int[distanceMatrix.length];
        int[] factories = new int[flowMatrix.length];

        greedy.assignLocation(1, locations, factories);

        assertTrue(Arrays.equals(locations, new int[]{0, 2, 0}));
        assertTrue(Arrays.equals(factories, new int[]{0, 2, 0}));
    }

    @Test
    void doFirstAssignment() {
        int[] locations = new int[distanceMatrix.length];
        int[] factories = new int[flowMatrix.length];

        greedy.doFirstAssignment(locations, factories);
    }

    @Test
    void findSolution(){
        int[] locations = new int[distanceMatrix.length];
        int[] factories = new int[flowMatrix.length];

        greedy.findSolution();
    }
}