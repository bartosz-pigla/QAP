public class Main {

    public static void main(String[] args){
        MatrixReader matrixReader = new MatrixReader("had12.dat.txt", "  ");
        matrixReader.read();

        Greedy greedy=new Greedy(matrixReader.getDistanceMatrix(),matrixReader.getFlowMatrix());
        Assignment solution = greedy.findSolution();
        System.out.println(solution);
    }
}
