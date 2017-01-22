package edu.agh.matik60.regression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matik60 on 15.01.2017.
 */
public class DataSet {

    private int rows;
    private int columns;
    private boolean hasColumnWithOnes; // Virtual column with ones
    private String[] header; // Columns labels
    private List<List<Double>> listOfDataRecords;
    private List<Double> listOfValues;

    public int rowsSize() {
        return rows;
    }

    public int columnsSize() {
        return columns;
    }

    public DataSet() {
        rows = 0;
        columns = 0;
        listOfDataRecords = new ArrayList<>();
        listOfValues = new ArrayList<>();
    }

    public Double getValue(int index) {
        return listOfValues.get(index);
    }

    public List<Double> getDataRecord(int index) {
        return listOfDataRecords.get(index);
    }

    public void generateFromCSV(String path, int columnValuesIndex) {
        listOfDataRecords.clear();
        listOfValues.clear();
        CSVReader r = new CSVReader(path);
        rows = r.getRows();
        columns = r.getColumns()-1;
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

        // Initializing header
        header = new String[columns];
        for (int i = 0; i < columns; i++) {
            header[i] = "x"+i;
        }
    }

    public String getHeader(int i) {return header[i];}

}
