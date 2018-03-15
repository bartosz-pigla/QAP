package utils;

import domain.Population;

public interface Logger<T> {

    void printAlgorithmInfo(T algorithm);
    void printStrongAvgWeak(int iterationNumber, Population population);
    void finishRun();
}
