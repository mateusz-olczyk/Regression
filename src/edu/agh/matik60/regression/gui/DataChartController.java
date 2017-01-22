package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.*;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Olczyk on 2017-01-20.
 */
public class DataChartController {

    @FXML
    private Label labelCSVFilePath;
    @FXML
    private Slider slider;
    @FXML
    private CheckBox checkBoxPolynomial;
    @FXML
    private Label labelCurrentCost;
    @FXML
    private Label labelCurrentCoefficients;
    @FXML
    private TableView<List<DoubleProperty>> tableView;
    @FXML
    private ScatterChart<Number, Number> scatterChart;

    private Stage primaryStage;

    private DataSet dataSet;
    private boolean isDataLoaded = false;
    private RegressionVisualiser regressionVisualiser;
    private DataVisualiser dataVisualiser;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private double getAlphaValue() {
        return Math.pow(10,-slider.getValue());
    }

    @FXML
    private void handleButtonLoadFromCSV() {
        //        FileChooser fileChooser = new FileChooser();
        //        fileChooser.setTitle("Open Resource File");
        //        fileChooser.showOpenDialog(primaryStage);
        if (!isDataLoaded) {
            isDataLoaded = true;
            dataSet = new DataSet();
            dataSet.generateFromCSV("example.csv",1);
            regressionVisualiser = new RegressionVisualiser(dataSet, scatterChart, labelCurrentCost, labelCurrentCoefficients);
            dataVisualiser = new DataVisualiser(dataSet, tableView);
        }
        regressionVisualiser.show();
        dataVisualiser.show();

    }

    @FXML
    private void handleCheckBoxPolynomial() {
        if (isDataLoaded) {
            regressionVisualiser.setPolynomial(checkBoxPolynomial.isSelected());
            regressionVisualiser.show();
        }
    }

    @FXML
    private void handleButtonReset() {
        if (isDataLoaded) {
            regressionVisualiser.reset();
            regressionVisualiser.show();
        }
    }

    private void performSteps(int howMany) {
        if (isDataLoaded) {
            for (int i = 0; i < howMany; i++) {
                regressionVisualiser.getRegression().gradientStep(getAlphaValue());
            }
            regressionVisualiser.show();
        }
    }

    @FXML
    private void handleButtonPerformOneStep() {
       performSteps(1);
    }

    @FXML
    private void handleButtonPerform1000Steps() {
        performSteps(1000);
    }

    @FXML
    private void handleButtonPerform10000Steps() {
        performSteps(10000);
    }

    @FXML
    private void initialize() {}
}
