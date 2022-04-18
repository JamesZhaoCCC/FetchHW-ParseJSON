package com.example.fetchhw;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IntComparator implements Comparator<Map<String, String>> {

    private final String key;

    public IntComparator(String key) {
        this.key = key;
    }

    @Override
    public int compare(Map<String, String> first,
                       Map<String, String> second) {
        Integer firstValue = Integer.parseInt(first.get(key));
        Integer secondValue = Integer.parseInt(second.get(key));
        return firstValue.compareTo(secondValue);
    }
}
