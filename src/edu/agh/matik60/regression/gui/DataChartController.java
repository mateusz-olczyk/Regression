package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Olczyk on 2017-01-20.
 */
public class DataChartController {

    @FXML
    private Label labelCSVFilePath;
    @FXML
    private TableView<List<DoubleProperty>> tableview;

//    private ObservableList<IntegerProperty> list = FXCollections.observableArrayList();

    private DataSet ds = new DataSet();

    @FXML
    private void handleButtonLoadFromCSV() {
        initTableView();
    }

    @FXML
    private void handleButtonReset() {
        //labelCSVFilePath.setText("Hello");
//        tableview.getColumns().add(new TableColumn<IntegerProperty,Integer>("first"));
//        list.clear();
    }

    @FXML
    private void initialize() {
        //tablecolumnX.setCellValueFactory(cell -> cell.getValue().asObject());
    }

    private void initTableView() {
        tableview.getColumns().clear();
        ds.generateFromCSV("example.csv",1);

        TableColumn<List<DoubleProperty>,Double> tablecolumnValue = new TableColumn<>("value");
        tableview.getColumns().add(tablecolumnValue);
        tablecolumnValue.setCellValueFactory(c->c.getValue().get(0).asObject());
        for (int i = 0; i < ds.getColumns(); i++) {
            TableColumn<List<DoubleProperty>,Double> tablecolumnData = new TableColumn<>(ds.getHeader(i));
            tableview.getColumns().add(tablecolumnData);
            final int j = i+1;
            tablecolumnData.setCellValueFactory(c->c.getValue().get(j).asObject());
        }

        ObservableList<List<DoubleProperty>> items = FXCollections.observableArrayList();
        tableview.setItems(items);
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
