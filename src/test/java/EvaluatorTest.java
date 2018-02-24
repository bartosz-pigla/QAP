import static org.junit.jupiter.api.Assertions.assertEquals;

class EvaluatorTest {
    private int[][] flowMatrix = {
            {0, 6, 4},
            {6, 0, 3},
            {4, 3, 0}
    };
    private int[][] distanceMatrix = {
            {0, 9, 5},
            {9, 0, 7},
            {5, 7, 0}
    };

    private Evaluator evaluator = new Evaluator(distanceMatrix, flowMatrix);

//    @org.junit.jupiter.api.BeforeEach
//    void setUp() {
//
//    }

    @org.junit.jupiter.api.Test
    void getCost() {
        Assignment input = new Assignment(new int[]{0, 1, 2}, new int[]{0, 2, 1});
        int output = evaluator.getCost(input);
        int expected = 57;
        assertEquals(output, expected);
    }
}