package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(of = {"locations", "factories"})
@ToString
@Data
@AllArgsConstructor
public class Solution {
    int[] locations;
    int[] factories;

    int cost;

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

    public static Solution copyOf(Solution original) {
        int length = original.locations.length;
        int[] locationsCopy = new int[length];
        System.arraycopy(original.locations, 0, locationsCopy, 0, length);
        int[] factoriesCopy = new int[length];
        System.arraycopy(original.factories, 0, factoriesCopy, 0, length);
        return new Solution(locationsCopy, factoriesCopy, original.getCost());
    }

}
