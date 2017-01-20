package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * Created by Mateusz Olczyk on 2017-01-20.
 */
public class DataChartController {

    @FXML
    private Label labelCSVFilePath;
    @FXML
    private TableColumn<obj, String> tablecolumnX;
    @FXML
    private TableView<obj> tableview;

    DataSet ds;

    class obj {

        StringProperty example;

        obj(String s) {
            example = new SimpleStringProperty(s);
        }

        public String getExample() {
            return example.get();
        }

        public StringProperty exampleProperty() {
            return example;
        }
    }

    @FXML
    private void handleButtonLoadFromCSV() {
//        labelCSVFilePath.setText("Button clicked!!!");
        ObservableList<obj> list = FXCollections.observableArrayList();
        list.add(new obj("a"));
        list.add(new obj("b"));
        tableview.setItems(list);
//        tablecolumnX.setCellFactory(c->c.getValue().exampleProperty());
        labelCSVFilePath.setText(list.get(0).toString());


//        tablecolumnX.setCellFactory
    }

}
