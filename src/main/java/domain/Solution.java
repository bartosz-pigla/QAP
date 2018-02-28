package domain;

import java.util.Arrays;

public class Solution {
    private int[] locations;
    private int[] factories;

    public Solution(int[] locations, int[] factories) {
        this.locations = locations;
        this.factories = factories;
    }

    public int getLocation(int locationNumber) {
        return locations[locationNumber];
    }

    public int getFactory(int factoryNumber) {
        return factories[factoryNumber];
    }

    public int[] getLocations() {
        return locations;
    }

    public int[] getFactories() {
        return factories;
    }

    public static Solution copyOf(Solution original) {
        int length = original.locations.length;
        int[] locationsCopy = new int[length];
        System.arraycopy(original.locations, 0, locationsCopy, 0, length);
        int[] factoriesCopy = new int[length];
        System.arraycopy(original.factories, 0, factoriesCopy, 0, length);
        return new Solution(locationsCopy, factoriesCopy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution that = (Solution) o;
        return Arrays.equals(locations, that.locations) &&
                Arrays.equals(factories, that.factories);
    }

    @Override
    public int hashCode() {

        int result = Arrays.hashCode(locations);
        result = 31 * result + Arrays.hashCode(factories);
        return result;
    }

    @Override
    public String toString() {
        return "domain.Solution{" +
                "locations=" + Arrays.toString(locations) +
                ", factories=" + Arrays.toString(factories) +
                '}';
    }
}
