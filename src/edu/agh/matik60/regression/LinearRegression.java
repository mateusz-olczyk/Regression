package edu.agh.matik60.regression;

import static java.lang.Math.pow;

/**
 * Created by Mateusz Olczyk on 2017-01-17.
 */
public class LinearRegression {

    protected DataSet ds;
    protected Vector coefficients;

    public void resetCoefficients() {
        coefficients.reset();
    }

    private int N() {
        return ds.rowsSize();
    }

    private int C() {
        return coefficients.size();
    }

    private double Y(int index) {
        return ds.getValue(index);
    }

    protected Vector getVector(int index) {
        Vector v = new Vector(ds.getDataRecord(index));
        v.addAuxiliaryOne();
        return v;
    }

    public LinearRegression(DataSet ds) {
        this.ds = ds;
        coefficients = new Vector(ds.columnsSize()+1);
    }

    public double getCost() {
        double cost = 0;
        for (int i = 0; i < N(); i++) {
            cost += pow(getVector(i).scalarProduct(coefficients)-Y(i), 2);
        }
        cost /= N();
        return cost;
    }

    private Vector getDerivative() {
        Vector result = new Vector(C());
        for (int ci = 0; ci < C(); ci++) {
            double di = 0;
            for (int i = 0; i < N(); i++) {
                di += (getComputedValue(i)-Y(i))*getVector(i).get(ci);
            }
            di = di*2/N();
            result.set(ci,di);
        }
        return result;
    }

    public double getComputedValue(int index) {
        return getVector(index).scalarProduct(coefficients);
    }

    public void gradientStep(double alpha) {
        coefficients.add(getDerivative().multiply(-alpha));
    }

    public String getCoefficientsAsString() {
        return coefficients.toString();
    }
}
