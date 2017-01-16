package edu.agh.matik60.regression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matik60 on 15.01.2017.
 */
public class DataSet {

    private int rows;
    private int columns_separation; // Determines how many columns from the beginning are treated as external data, the rest are auxiliary
    private boolean flag_column_one; // Virtual column with ones
    private List<String> columns_labels;
    private List<List<Double>> columns_data;
    private List<Double> column_values;

}
