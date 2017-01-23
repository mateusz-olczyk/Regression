package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mateusz Olczyk on 2017-01-22.
 */
public class ColumnSettingsController {

    @FXML
    private TextField textFieldColumnWithValues;
    @FXML
    private TextField textFieldArgumentForChart;
    @FXML
    private void handleButtonOK() {
        int columnWithValues;
        int argumentForChart;
        try {
            columnWithValues = Integer.parseInt(textFieldColumnWithValues.getText());
            argumentForChart = Integer.parseInt(textFieldArgumentForChart.getText());
            if (0<=columnWithValues && columnWithValues<max_columns && 0<=argumentForChart && argumentForChart<max_columns-1) {
                owner.setColumnWithValues(columnWithValues);
                owner.setArgumentForChart(argumentForChart);
                ok = true;
                stage.close();
            } else {
                showError();
            }
        } catch (NumberFormatException e) {
            showError();
        }
    }
    @FXML
    private void handleButtonCancel() {
        stage.close();
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Settings error");
        alert.setContentText("Invalid column indexes.");
        alert.showAndWait();
    }

    private Stage stage;
    private int max_columns;
    private boolean ok = false;
    private DataChartController owner;

    public static boolean showDialog(DataChartController owner, int max_columns) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/ColumnSettings.fxml"));
            Scene scene = new Scene((Pane) loader.load());
            Stage stage = new Stage();
            stage.setTitle("Column settings");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initOwner(owner.getPrimaryStage());
            ((ColumnSettingsController)loader.getController()).max_columns = max_columns;
            ((ColumnSettingsController)loader.getController()).stage = stage;
            ((ColumnSettingsController)loader.getController()).owner = owner;
            stage.showAndWait();
            return ((ColumnSettingsController)loader.getController()).ok;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
