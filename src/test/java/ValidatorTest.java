import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    private Validator validator = new Validator();

    @Test
    void shouldReturnFalseWhenNoDistinctValuesInBothArraysWith2Elements() {
        int[] locations = new int[]{0,0};
        int[] factories = new int[]{0,0};
        int stopIdx=1;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnFalseWhenNoDistinctValuesInLocationsWith2Elements() {
        int[] locations = new int[]{0,0};
        int[] factories = new int[]{0,1};
        int stopIdx=1;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnFalseWhenNoDistinctValuesInFactoriesWith2Elements() {
        int[] locations = new int[]{0,1};
        int[] factories = new int[]{0,0};
        int stopIdx=1;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnTrueWhenDistinctValuesInBothArraysWith2Elements() {
        int[] locations = new int[]{0,1};
        int[] factories = new int[]{0,1};
        int stopIdx=1;
        assertTrue(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnFalseWhenNoDistinctValuesInBothArraysWith3Elements() {
        int[] locations = new int[]{0,0,0};
        int[] factories = new int[]{0,0,0};
        int stopIdx=2;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnFalseWhenNoDistinctValuesInLocationsWith3Elements() {
        int[] locations = new int[]{0,0,0};
        int[] factories = new int[]{0,1,2};
        int stopIdx=2;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnFalseWhenNoDistinctValuesInFactoriesWith3Elements() {
        int[] locations = new int[]{0,1,2};
        int[] factories = new int[]{0,1,0};
        int stopIdx=2;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnTrueWhenDistinctValuesInBothArraysWith3Elements() {
        int[] locations = new int[]{0,1,2};
        int[] factories = new int[]{0,1,2};
        int stopIdx=1;
        assertTrue(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnFalseWhenDistinctValuesInFactoriesWith3Elements() {
        int[] locations = new int[]{0,1,0};
        int[] factories = new int[]{0,1,2};
        int stopIdx=2;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }

    @Test
    void shouldReturnTrueWhenDistinctValuesInBothArraysWith5Elements() {
        int[] locations = new int[]{0,1,1,1,1};
        int[] factories = new int[]{0,1,1,1,1};
        int stopIdx=4;
        assertFalse(validator.isValid(stopIdx,locations,factories));
    }
}