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

    private ISymptomReader reader;
    private ISymptomWriter writer;

    /**
     * Constructor for the AnalyticsCounter class.
     * Initializes the reader and writer objects used for reading symptoms and writing results.
     * 
     * @param reader An implementation of ISymptomReader to read the symptoms from a data source
     * @param writer An implementation of ISymptomWriter to write the symptoms and their counts to an output file
     */
    public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Reads the list of symptoms from the input file.
     * 
     * @return A list of symptoms as strings, potentially with duplicates
     */
    public List<String> getSymptoms() {
        return reader.GetSymptoms();
    }

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

    /**
     * Writes the symptoms and their occurrences to an output file.
     * 
     * @param symptoms A map of symptoms and their counts to write to the output file
     */
    public void writeSymptoms(Map<String, Integer> symptoms) {
        writer.writeSymptoms(symptoms);
    }

    /**
     * The main method that drives the program execution.
     * It reads symptoms from a file, counts and sorts them, and writes the results to an output file.
     * 
     * @param args Command-line arguments (not used)
     * @throws Exception If an error occurs during file I/O operations
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Début du programme");
        
        // Initialize reader and writer with file paths
        ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse\\symptoms.txt");
        ISymptomWriter writer = new WriteSymptomDataToFile("Project02Eclipse\\result.out");

        // Create an instance of AnalyticsCounter
        AnalyticsCounter counter = new AnalyticsCounter(reader, writer);
        
        // Read symptoms from the input file
        System.out.println("Lecture des symptômes");
        List<String> symptoms = counter.getSymptoms();
        
        // Count occurrences of each symptom
        System.out.println("Comptage des symptômes");
        Map<String, Integer> symptomCounts = counter.countSymptoms(symptoms);
        
        // Sort the symptoms alphabetically
        System.out.println("Tri des symptômes");
        Map<String, Integer> sortedSymptoms = counter.sortSymptoms(symptomCounts);
        
        // Write the sorted symptom counts to the output file
        System.out.println("Écriture des symptômes");
        counter.writeSymptoms(sortedSymptoms);
        
        System.out.println("Fin du programme");
    }
}
