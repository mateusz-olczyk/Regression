package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.DataSet;
import edu.agh.matik60.regression.LinearRegression;
import edu.agh.matik60.regression.PolynomialRegression;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * Created by Mateusz Olczyk on 2017-01-22.
 */
public class RegressionVisualiser {

    private DataSet dataSet;
    private ScatterChart<Number, Number> scatterChart;
    private LinearRegression linearRegression;
    private PolynomialRegression polymonialRegression;
    private XYChart.Series<Number, Number> seriesRawData;
    private XYChart.Series<Number,Number> seriesRegression;

    public boolean isPolymonial() {
        return polymonial;
    }

    public void setPolymonial(boolean polymonial) {
        this.polymonial = polymonial;
    }

    private boolean polymonial;

    public LinearRegression getRegression() {
        return isPolymonial()?polymonialRegression:linearRegression;
    }

    public RegressionVisualiser(DataSet dataSet, ScatterChart<Number, Number> scatterChart) {
        this.dataSet = dataSet;
        this.scatterChart = scatterChart;
        polymonial = false;
        linearRegression = new LinearRegression(dataSet);
        polymonialRegression = new PolynomialRegression(dataSet);
    }

    public void show() {
        seriesRawData = new XYChart.Series<>();
        seriesRawData.setName("Data from CSV");
        seriesRegression = new XYChart.Series<>();
        seriesRegression.setName("Regression");

        scatterChart.getData().clear();
        for (int i = 0; i < dataSet.rowsSize(); i++) {
            Double x = dataSet.getDataRecord(i).get(0);
            Double y = dataSet.getValue(i);
            seriesRegression.getData().add(new XYChart.Data<>(x, getRegression().getComputedValue(i)));
            seriesRawData.getData().add(new XYChart.Data<>(x, y));
        }

        scatterChart.getData().add(seriesRawData);
        scatterChart.getData().add(seriesRegression);

    }


}
