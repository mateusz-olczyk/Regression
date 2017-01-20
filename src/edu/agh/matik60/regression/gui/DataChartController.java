package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
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

//    private ObservableList<IntegerProperty> list = FXCollections.observableArrayList();

    private DataSet ds = new DataSet();

    @FXML
    private void handleButtonLoadFromCSV() {
        initTableView();
        initScatterChart();
    }

    @FXML
    private void handleButtonReset() {
        //labelCSVFilePath.setText("Hello");
//        tableView.getColumns().add(new TableColumn<IntegerProperty,Integer>("first"));
//        list.clear();
    }

    @FXML
    private void initialize() {
        //tablecolumnX.setCellValueFactory(cell -> cell.getValue().asObject());
    }

    private void initScatterChart() {
        scatterChart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data from CSV");
        for (int i = 0; i < ds.getRows(); i++) {
            Double x = ds.getDataRecord(i).get(0);
            Double y = ds.getValue(i);
            series.getData().add(new XYChart.Data<>(x, y));
        }
        scatterChart.getData().add(series);
    }

    private void initTableView() {
        tableView.getColumns().clear();
        ds.generateFromCSV("example.csv",1);

        TableColumn<List<DoubleProperty>,Double> tableColumnValue = new TableColumn<>("value");
        tableView.getColumns().add(tableColumnValue);
        tableColumnValue.setCellValueFactory(c->c.getValue().get(0).asObject());
        for (int i = 0; i < ds.getColumns(); i++) {
            TableColumn<List<DoubleProperty>,Double> tablecolumnData = new TableColumn<>(ds.getHeader(i));
            tableView.getColumns().add(tablecolumnData);
            final int j = i+1;
            tablecolumnData.setCellValueFactory(c->c.getValue().get(j).asObject());
        }

        ObservableList<List<DoubleProperty>> items = FXCollections.observableArrayList();
        tableView.setItems(items);
        for (int i = 0; i < ds.getRows(); i++) {
            List<DoubleProperty> item = new ArrayList<>();
            item.add(new SimpleDoubleProperty(ds.getValue(i)));
            for (int j = 0; j < ds.getColumns(); j++) {
                item.add(new SimpleDoubleProperty(ds.getDataRecord(i).get(j)));
            }
            items.add(item);
        }

    }
}
