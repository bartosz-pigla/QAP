package domain;

import lombok.*;

@EqualsAndHashCode(of = {"locations", "factories"})
@ToString
public class Solution {
    @Getter
    int[] locations;

    @Getter
    int[] factories;

    public Solution(int[] locations, int[] factories) {
        this.locations = locations;
        this.factories = factories;
    }

    @Getter
    @Setter
    int cost;

    public int getLocation(int locationNumber) {
        return locations[locationNumber];
    }

    public int getFactory(int factoryNumber) {
        return factories[factoryNumber];
    }

    public static Solution copyOf(Solution original) {
        int length = original.locations.length;
        int[] locationsCopy = new int[length];
        System.arraycopy(original.locations, 0, locationsCopy, 0, length);
        int[] factoriesCopy = new int[length];
        System.arraycopy(original.factories, 0, factoriesCopy, 0, length);
        return new Solution(locationsCopy, factoriesCopy);
    }

}
