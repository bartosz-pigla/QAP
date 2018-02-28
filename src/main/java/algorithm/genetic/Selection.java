package algorithm.genetic;

import domain.Population;
import domain.Solution;

public interface Selection {
    Solution select(Population population, Solution solutionToExclude);
}
