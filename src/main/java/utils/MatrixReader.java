package utils;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatrixReader {
    private URL url;
    private int matrixSize;

    @Getter
    private int[][] flowMatrix;
    @Getter
    private int[][] distanceMatrix;

    public MatrixReader(URL url) {
        this.url = url;
    }

    public void read() {
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
            Scanner scanner = new Scanner(line);
            matrixSize = scanner.nextInt();
        } else {
            System.out.println("FILE IS EMPTY");
            throw new IOException();
        }
    }

    private void fillMatrix(int matrix[][], BufferedReader bufferedReader) throws IOException {
        String line;
        int i = 0;

        while ((line = bufferedReader.readLine()) != null && i < matrixSize) {
            matrix[i] = convertToIntArray(line);
            i++;
        }
    }

    private int[] convertToIntArray(String line) {
        int[] intArray = new int[matrixSize];

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);

        for (int i = 0; i < intArray.length; i++) {
            matcher.find();
            String number = matcher.group(0);
            intArray[i] = Integer.parseInt(number);
        }

        return intArray;
    }
}
