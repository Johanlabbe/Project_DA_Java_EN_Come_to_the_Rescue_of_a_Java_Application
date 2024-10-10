package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class AnalyticsCounter {

	private ISymptomReader reader;
    private ISymptomWriter writer;

    public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

	public List<String> getSymptoms() {
		return reader.GetSymptoms();
	}

	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomCount = new HashMap<>();
		for (String symptom : symptoms) {
			symptomCount.put(symptom, symptomCount.getOrDefault(symptom, 0) + 1);
		}
		return symptomCount;
	}

	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		return symptoms.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

	public void writeSymptoms(Map<String, Integer> symptoms) {
		writer.writeSymptoms(symptoms);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Début du programme");
		
		ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse\\symptoms.txt");
		ISymptomWriter writer = new WriteSymptomDataToFile("Project02Eclipse\\result.out");
	
		AnalyticsCounter counter = new AnalyticsCounter(reader, writer);
		
		System.out.println("Lecture des symptômes");
		List<String> symptoms = counter.getSymptoms();
		
		System.out.println("Comptage des symptômes");
		Map<String, Integer> symptomCounts = counter.countSymptoms(symptoms);
		
		System.out.println("Tri des symptômes");
		Map<String, Integer> sortedSymptoms = counter.sortSymptoms(symptomCounts);
		
		System.out.println("Écriture des symptômes");
		counter.writeSymptoms(sortedSymptoms);
		
		System.out.println("Fin du programme");
	}

}
