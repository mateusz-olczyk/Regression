package edu.agh.matik60.regression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mateusz Olczyk on 2017-01-17.
 */
public class Vector {

    private List<Double> values;

    // Creates Vector with specified size and filled with zeros
    public Vector(int size) {
        values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            values.add(0.);
        }
    }

    public Vector(Double[] data) {
        values = new ArrayList<>(Arrays.asList(data));
    }

    public Vector(List<Double> data) {
        values = new ArrayList<>(data);
    }

    public double scalarProduct(Vector other) {
        // TODO Check if the vectors have the same size
        double result = 0;
        for (int i = 0; i < size();i++) {
            result += this.get(i) * other.get(i);
        }
        return result;
    }

    public Vector multiply(double alpha) {
        for (int i = 0; i < values.size(); i++) {
            values.set(i, values.get(i)*alpha);
        }
        return this;
    }

    public Vector add(Vector other) {
        // TODO Check if the vectors have the same size
        for (int i = 0; i < values.size(); i++) {
            values.set(i, values.get(i)+other.get(i));
        }
        return this;
    }

    public void addAuxiliaryOne() {
        values.add(1.);
    }

    public Double get(int index) {
        return values.get(index);
    }

    public Double set(int index, Double element) {
        return values.set(index, element);
    }

    public int size() {
        return values.size();
    }

    @Override
    public String toString() {
        String result = "[ ";
        for (Double value : values) {
            result += value + " ";
        }
        result += "]";
        return result;
    }
}
