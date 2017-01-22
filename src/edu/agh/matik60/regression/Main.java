package edu.agh.matik60.regression;

import edu.agh.matik60.regression.gui.DataChartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/DataChart.fxml"));
            Scene scene = new Scene((AnchorPane) loader.load());
            ((DataChartController)loader.getController()).setPrimaryStage(primaryStage);
            primaryStage.setTitle("Regression");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
