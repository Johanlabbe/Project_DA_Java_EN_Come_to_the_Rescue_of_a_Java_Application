package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class Main {

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
        AnalyticsCounter counter = new AnalyticsCounter();
        
        // Read symptoms from the input file
        System.out.println("Lecture des symptômes");
        List<String> symptoms = reader.getSymptoms();
        
        // Count occurrences of each symptom
        System.out.println("Comptage des symptômes");
        Map<String, Integer> symptomCounts = counter.countSymptoms(symptoms);
        
        // Sort the symptoms alphabetically
        System.out.println("Tri des symptômes");
        Map<String, Integer> sortedSymptoms = counter.sortSymptoms(symptomCounts);
        
        // Write the sorted symptom counts to the output file
        System.out.println("Écriture des symptômes");
        writer.writeSymptoms(sortedSymptoms);
        
        System.out.println("Fin du programme");
    }
}
