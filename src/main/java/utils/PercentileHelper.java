package utils;

import java.util.Collections;
import java.util.List;

public class PercentileHelper {

    public static int Percentile(List<Integer> boostersUsed, double percentile) {
        Collections.sort(boostersUsed);
        int index = (int) Math.ceil((percentile / 100) * boostersUsed.size());
        return boostersUsed.get(index - 1);
    }
}
