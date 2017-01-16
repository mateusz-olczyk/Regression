package edu.agh.matik60.regression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by matik60 on 16.01.2017.
 */
public class CSVReader implements Iterable<Double[]> {

    private String csvFilePath;
    private String delimiter;
    private List<Double[]> data;

    public String getCsvFilePath() {
        if (csvFilePath == null) return "";
        return csvFilePath;
    }

    public CSVReader(String path) {
        csvFilePath = path;
        delimiter = ",";
        data = new ArrayList<>();
    }

    private void loadData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getCsvFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(delimiter);
                int l = strings.length;
                Double[] values = new Double[l];
                for (int i=0;i<l;i++) {
                    values[i] = Double.parseDouble(strings[i]);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<Double[]> iterator() {
        return data.iterator();
    }

}
