import java.util.Arrays;

//TO DO: ASSIGNMENT POOL
public class Assignment {
    private int[] permutation;

    public Assignment(int[] permutation) {
        this.permutation = permutation;
    }

    public Assignment(int locationQuantity) {
        permutation=new int[locationQuantity];
    }

    public static Assignment copyOf(Assignment original){
        int [] copy=new int[original.permutation.length];
        for(int i=0;i<original.permutation.length;i++){
            copy[i]=original.permutation[i];
        }
        return new Assignment(copy);
    }


}
