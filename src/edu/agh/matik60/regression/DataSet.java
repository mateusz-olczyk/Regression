package edu.agh.matik60.regression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matik60 on 15.01.2017.
 */
public class DataSet {

    private int rows;
    private int columnsSeparation; // Determines how many columns from the beginning are treated as external data, the rest are auxiliary
    private boolean hasColumnWithOnes; // Virtual column with ones
    private String[] header; // Columns labels
    private List<List<Double>> listOfDataRecords;
    private List<Double> listOfValues;

    public DataSet() {
        rows = 0;
        columnsSeparation = 0;
        hasColumnWithOnes = true;
        listOfDataRecords = new ArrayList<>();
        listOfValues = new ArrayList<>();
    }

    public void generateFromCSV(String path, int columnValuesIndex) {
        CSVReader r = new CSVReader(path);
        columnsSeparation = r.getColumns()-1;
        rows = r.getRows();
        for (Double[] rowFromCSV : r) {
            // Prepare next record
            List<Double> rowData = new ArrayList<>();
            for (int i=0;i<rowFromCSV.length;i++) {
                if (i == columnValuesIndex)
                    listOfValues.add(rowFromCSV[i]);
                else
                    rowData.add(rowFromCSV[i]);
            }
            // Add record to listOfDataRecords
            listOfDataRecords.add(rowData);
        }
    }

}
