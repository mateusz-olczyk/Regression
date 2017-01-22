package edu.agh.matik60.regression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by matik60 on 16.01.2017.
 */
public class CSVReader implements Iterable<Double[]> {

    private File file;
    private String delimiter;
    private List<Double[]> data;
    private int columns;

    public int getColumnsSize() {
        return columns;
    }

    public int getRowsSize() {
        return data.size();
    }

    public CSVReader(File file) throws CSVReaderException {
        this.file = file;
        delimiter = ",";
        data = new ArrayList<>();
        columns = 0;
        loadData();
    }

    private void loadData() throws CSVReaderException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(delimiter);
                int l = strings.length;
                // Number of columns must be constant
                if (columns != 0 && l != columns) throw new CSVReaderException();
                columns = l;
                Double[] values = new Double[l];
                for (int i=0;i<l;i++) {
                    values[i] = Double.parseDouble(strings[i]);
                }
                data.add(values);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new CSVReaderException();
        }
    }

    @Override
    public Iterator<Double[]> iterator() {
        return data.iterator();
    }

}
