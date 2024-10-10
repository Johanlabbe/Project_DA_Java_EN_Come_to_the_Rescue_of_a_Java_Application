package com.hemebiotech.analytics;

import java.util.Map;

public interface ISymptomWriter {
    /**
     * Writes the symptoms and their counts to a file
     *
     * @param symptoms a map with symptom names as keys and their counts as values
     */
    public void writeSymptoms(Map<String, Integer> symptoms);
}
