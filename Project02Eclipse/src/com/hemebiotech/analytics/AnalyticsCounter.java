package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The AnalyticsCounter class is responsible for reading symptom data from a file,
 * counting the occurrences of each symptom, sorting them, and writing the results
 * to an output file. It uses ISymptomReader to read and ISymptomWriter to write.
 */
public class AnalyticsCounter {
    
    /**
     * Counts the occurrences of each symptom in the provided list.
     * 
     * @param symptoms A list of symptoms, potentially with duplicates
     * @return A map where the key is the symptom name and the value is the number of occurrences
     */
    public Map<String, Integer> countSymptoms(List<String> symptoms) {
        Map<String, Integer> symptomCount = new HashMap<>();
        for (String symptom : symptoms) {
            // Increment the count for each symptom using getOrDefault to handle new entries
            symptomCount.put(symptom, symptomCount.getOrDefault(symptom, 0) + 1);
        }
        return symptomCount;
    }

    /**
     * Sorts the symptoms in alphabetical order by symptom name.
     * 
     * @param symptoms A map where the key is the symptom name and the value is the number of occurrences
     * @return A sorted map with symptoms in alphabetical order
     */
    public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
        // Sorts the symptoms by their keys (symptom names) and returns a new LinkedHashMap
        return symptoms.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
    
}
