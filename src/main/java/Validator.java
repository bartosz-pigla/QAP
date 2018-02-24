import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    Set<Integer> distinctValues=new HashSet<>();

    public boolean isValid(int stopIdx, int[] locations, int[] factories) {
        if (arrayContainsDistinctValues(stopIdx, locations) && arrayContainsDistinctValues(stopIdx, factories)) {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean arrayContainsDistinctValues(int stopIdx, int[] array) {
//        if(Arrays.equals(array,new int[]{1,2,1})){
//            System.out.println("ARRAYS");
//        }

        distinctValues.clear();

        for(int i=0;i<=stopIdx;i++){
            distinctValues.add(array[i]);
        }

        return distinctValues.size()==stopIdx+1;
    }

}
