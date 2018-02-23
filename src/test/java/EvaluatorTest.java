import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {
    private int[][] flowMatrix={
            {0,6,4},
            {6,0,3},
            {4,3,0}
    };
    private int[][] distanceMatrix={
            {0,9,5},
            {9,0,7},
            {5,7,0}
    };

    private Evaluator evaluator=new Evaluator(flowMatrix,distanceMatrix);

//    @org.junit.jupiter.api.BeforeEach
//    void setUp() {
//
//    }

    @org.junit.jupiter.api.Test
    void getCost() {
        int[]permutation={1,3,2};
        assertEquals(evaluator.getCost(permutation),41);
    }
}