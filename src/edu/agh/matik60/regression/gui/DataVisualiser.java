package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.DataSet;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Olczyk on 2017-01-22.
 */
public class DataVisualiser {

    private DataSet dataSet;
    private TableView<List<DoubleProperty>> tableView;

    public DataVisualiser(DataSet dataSet, TableView<List<DoubleProperty>> tableView) {
        this.dataSet = dataSet;
        this.tableView = tableView;
    }

    public void show() {
        tableView.getColumns().clear();

        TableColumn<List<DoubleProperty>,Double> tableColumnValue = new TableColumn<>("value");
        tableView.getColumns().add(tableColumnValue);
        tableColumnValue.setCellValueFactory(c->c.getValue().get(0).asObject());
        for (int i = 0; i < dataSet.columnsSize(); i++) {
            TableColumn<List<DoubleProperty>,Double> tablecolumnData = new TableColumn<>(dataSet.getHeader(i));
            tableView.getColumns().add(tablecolumnData);
            final int j = i+1;
            tablecolumnData.setCellValueFactory(c->c.getValue().get(j).asObject());
        }

        ObservableList<List<DoubleProperty>> items = FXCollections.observableArrayList();
        tableView.setItems(items);
        for (int i = 0; i < dataSet.rowsSize(); i++) {
            List<DoubleProperty> item = new ArrayList<>();
            item.add(new SimpleDoubleProperty(dataSet.getValue(i)));
            for (int j = 0; j < dataSet.columnsSize(); j++) {
                item.add(new SimpleDoubleProperty(dataSet.getDataRecord(i).get(j)));
            }
            items.add(item);
        }
    }
}
