package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Olczyk on 2017-01-20.
 */
public class DataChartController {

    @FXML
    private Label labelCSVFilePath;
    @FXML
    private TableView<List<DoubleProperty>> tableView;
    @FXML
    private ScatterChart<Number, Number> scatterChart;

    private DataSet dataSet;
    private RegressionVisualiser regressionVisualiser;
    private DataVisualiser dataVisualiser;

    @FXML
    private void handleButtonLoadFromCSV() {
        if (dataSet == null) {
            dataSet = new DataSet();
            dataSet.generateFromCSV("example.csv",1);
            regressionVisualiser = new RegressionVisualiser(dataSet, scatterChart);
            dataVisualiser = new DataVisualiser(dataSet, tableView);
        }
        regressionVisualiser.show();
        dataVisualiser.show();

    }

    @FXML
    private void handleButtonReset() {

    }

    @FXML
    private void initialize() {
//        regressionVisualiser = new RegressionVisualiser(dataSet, scatterChart);
    }
}
