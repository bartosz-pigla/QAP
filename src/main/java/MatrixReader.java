import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class MatrixReader {
    private String filename;
    private String separator;

    private int matrixSize;

    private int[][] flowMatrix;
    private int[][] distanceMatrix;

    public MatrixReader(String filename, String separator) {
        this.filename = filename;
        this.separator = separator;
    }

    public void read() {
        URL url = getClass().getResource(filename);
        File file = new File(url.getPath());

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            setMatrixSize(bufferedReader);
            flowMatrix = new int[matrixSize][];
            distanceMatrix = new int[matrixSize][];

            bufferedReader.readLine();
            fillMatrix(flowMatrix, bufferedReader);
            fillMatrix(distanceMatrix, bufferedReader);

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMatrixSize(BufferedReader bufferedReader) throws IOException {
        String line;
        if ((line = bufferedReader.readLine()) != null) {
            matrixSize = Integer.parseInt(line.substring(separator.length()));
        } else {
            System.out.println("FILE IS EMPTY");
            throw new IOException();
        }
    }

    private void fillMatrix(int matrix[][], BufferedReader bufferedReader) throws IOException {
        String line;
        int i = 0;

        while ((line = bufferedReader.readLine()) != null && i<matrixSize) {
            String[] row = line.substring(separator.length()).split(separator);
            matrix[i] = convertToIntArray(row);
            i++;
        }
    }

    private int[] convertToIntArray(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    public int[][] getFlowMatrix() {
        return flowMatrix;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }
}
