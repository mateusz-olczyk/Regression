package edu.agh.matik60.regression;

/**
 * Created by Mateusz Olczyk on 2017-01-17.
 */
public class PolynomialRegression extends LinearRegression {

    public PolynomialRegression(DataSet ds) {
        super(ds);
        int data_size = ds.getColumns();
        coefficients = new Vector(data_size+1+(data_size+1)*data_size/2);
    }

    @Override
    protected Vector getVector(int index) {
        Vector v = new Vector(ds.getDataRecord(index));
        v.addAuxiliaryOne();
        v.addAuxiliaryPolynomials();
        return v;
    }
}
