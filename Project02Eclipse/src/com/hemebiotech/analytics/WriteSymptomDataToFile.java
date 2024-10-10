package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter {
    private String filepath;

    // Constructor to specify the output file path
    public WriteSymptomDataToFile(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        try (FileWriter writer = new FileWriter(filepath)) {
            for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

