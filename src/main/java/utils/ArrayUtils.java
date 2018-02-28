package utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ArrayUtils {
    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    public static int[] getSortedArray(int arrayLength) {
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int getDistinctValuesQuantityOfArray(int[] array) {
        Set<Integer> distinctValues = new HashSet<>(array.length);
        for (int i = 0; i < array.length; i++) {
            distinctValues.add(array[i]);
        }
        return distinctValues.size();
    }

}
