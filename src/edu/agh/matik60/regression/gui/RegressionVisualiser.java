package edu.agh.matik60.regression.gui;

import edu.agh.matik60.regression.DataSet;
import edu.agh.matik60.regression.LinearRegression;
import edu.agh.matik60.regression.PolynomialRegression;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * Created by Mateusz Olczyk on 2017-01-22.
 */
public class RegressionVisualiser {

    private DataSet dataSet;
    private ScatterChart<Number, Number> scatterChart;
    private Label labelCurrentCost;
    private Label labelCurrentCoefficients;

    private LinearRegression linearRegression;
    private PolynomialRegression polynomialRegression;
    private XYChart.Series<Number, Number> seriesRawData;
    private XYChart.Series<Number,Number> seriesRegression;

    public boolean isPolynomial() {
        return polynomial;
    }

    public void setPolynomial(boolean polynomial) {
        this.polynomial = polynomial;
    }

    private boolean polynomial;
    private  int argumentForChart;

    public LinearRegression getRegression() {
        return isPolynomial()? polynomialRegression :linearRegression;
    }

    public RegressionVisualiser(DataSet dataSet, ScatterChart<Number, Number> scatterChart, Label labelCurrentCost, Label labelCurrentCoefficients, int argumentForChart) {
        this.dataSet = dataSet;
        this.scatterChart = scatterChart;
        this.labelCurrentCost = labelCurrentCost;
        this.labelCurrentCoefficients = labelCurrentCoefficients;
        this.argumentForChart = argumentForChart;
        polynomial = false;
        linearRegression = new LinearRegression(dataSet);
        polynomialRegression = new PolynomialRegression(dataSet);
    }

    public void show() {
        seriesRawData = new XYChart.Series<>();
        seriesRawData.setName("Data from CSV");
        seriesRegression = new XYChart.Series<>();
        seriesRegression.setName("Regression");

        scatterChart.getData().clear();
        for (int i = 0; i < dataSet.rowsSize(); i++) {
            Double x = dataSet.getDataRecord(i).get(argumentForChart);
            Double y = dataSet.getValue(i);
            seriesRegression.getData().add(new XYChart.Data<>(x, getRegression().getComputedValue(i)));
            seriesRawData.getData().add(new XYChart.Data<>(x, y));
        }

        scatterChart.getData().add(seriesRawData);
        scatterChart.getData().add(seriesRegression);

        updateLabels();
    }

    public void reset() {
        linearRegression.resetCoefficients();
        polynomialRegression.resetCoefficients();
    }

    private void updateLabels() {
        labelCurrentCost.setText(String.valueOf(getRegression().getCost()));
        labelCurrentCoefficients.setText(getRegression().getCoefficientsAsString());
    }


}
