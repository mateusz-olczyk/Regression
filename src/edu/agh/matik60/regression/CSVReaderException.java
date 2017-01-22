package edu.agh.matik60.regression;

/**
 * Created by Mateusz Olczyk on 2017-01-22.
 */
public class CSVReaderException extends Exception {

    public CSVReaderException() {
        super("This is not valid CSV file.");
    }

}
