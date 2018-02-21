import java.util.Arrays;

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

    public int[] getPermutation() {
        return permutation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Arrays.equals(permutation, that.permutation);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(permutation);
    }
}
