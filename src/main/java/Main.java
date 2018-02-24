import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        MatrixReader matrixReader = new MatrixReader("had12.dat.txt", "  ");
        matrixReader.read();

        Greedy greedy = new Greedy(matrixReader.getDistanceMatrix(), matrixReader.getFlowMatrix());
        Assignment solution = greedy.findSolution();
        System.out.println(solution);

        System.out.println("NUMBER OF DISTINCT VALUES: LOCATION: " + getDistinctValuesQuantityOfArray(solution.getLocations()));
        System.out.println("NUMBER OF DISTINCT VALUES: FACTORIES: " + getDistinctValuesQuantityOfArray(solution.getFactories()));

    }

    public static int getDistinctValuesQuantityOfArray(int[] array) {
        Set<Integer> distinctValues = new HashSet<>(array.length);
        for (int i = 0; i < array.length; i++) {
            distinctValues.add(array[i]);
        }
        return distinctValues.size();
    }
}
