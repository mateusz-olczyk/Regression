package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.*;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Stage primaryStage;

    private DataSet dataSet;
    private boolean isDataLoaded = false;
    private RegressionVisualiser regressionVisualiser;
    private DataVisualiser dataVisualiser;

    public void setColumnWithValues(int columnWithValues) {
        this.columnWithValues = columnWithValues;
    }

    public void setArgumentForChart(int argumentForChart) {
        this.argumentForChart = argumentForChart;
    }

    private int columnWithValues;
    private int argumentForChart;

    private double getAlphaValue() {
        return Math.pow(10,-slider.getValue());
    }

    @FXML
    private void handleButtonLoadFromCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                CSVReader reader = new CSVReader(file);
                if (ColumnSettingsController.showDialog(this, reader.getColumnsSize())) {
                    labelCSVFilePath.setText(file.getAbsolutePath());
                    isDataLoaded = true;
                    dataSet = new DataSet();
                    dataSet.generateFromCSV(file,columnWithValues);
                    regressionVisualiser = new RegressionVisualiser(dataSet, scatterChart, labelCurrentCost, labelCurrentCoefficients, argumentForChart);
                    dataVisualiser = new DataVisualiser(dataSet, tableView);
                    regressionVisualiser.show();
                    dataVisualiser.show();
                }
            } catch (CSVReaderException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Loading error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }

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

    public static void loadAndShow(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/DataChart.fxml"));
            Scene scene = new Scene((AnchorPane) loader.load());
            ((DataChartController)loader.getController()).primaryStage = primaryStage;
            primaryStage.setTitle("Regression");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
