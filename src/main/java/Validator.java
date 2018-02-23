public class Validator {
    public boolean isValid(int stopIdx, int[] locations, int[] factories) {
        if (arrayContainsDistinctValues(stopIdx, locations) && arrayContainsDistinctValues(stopIdx, factories)) {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean arrayContainsDistinctValues(int stopIdx, int[] array) {
        for (int i = 0; i <= stopIdx - 1; i++) {
            for (int j = i + 1; j <= stopIdx; j++) {
                if (array[i] == array[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
