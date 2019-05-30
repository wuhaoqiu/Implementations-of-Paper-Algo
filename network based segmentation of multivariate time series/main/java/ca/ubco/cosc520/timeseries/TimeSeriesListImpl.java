package ca.ubco.cosc520.timeseries;

import ca.ubco.cosc520.matrix.SingleMatrixOperator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * A list of multiple time series data. All of the time series must be of the same length. Time
 * series data is expected and returned as an array of doubles.
 */
public class TimeSeriesListImpl implements TimeSeriesList {

  /**
   * Internal arraylist to store series.
   */
  private final List<double[]> seriesList = new ArrayList<>();

  /**
   * Initialize a new TimeSeriesList from a list of doubles.
   *
   * @param doubleList The list of doubles.
   * @return A new TimeSeriesList
   */
  public static TimeSeriesList fromDoubleList(
      @NonNull final List<double[]> doubleList) {

    TimeSeriesList ts = new TimeSeriesListImpl();
    doubleList.forEach(ts::add);
    return ts;
  }

  public static TimeSeriesList fromDoubleArray(
      @NonNull final double[][] doubleArray) {
    List<double[]> doubleList = Arrays.asList(doubleArray);
    return fromDoubleList(doubleList);
  }

  @Override
  public final void add(@NonNull final double[] ts) {
    seriesList.add(ts);
  }

  @Override
  public final double[] getByIndex(final int idx) {
    return seriesList.get(idx);
  }

  @Override
  public final List<double[]> getList() {
    return Collections.unmodifiableList(seriesList);
  }

  @Override
  public final TimeSeriesList truncate(final int start, final int end) {
    return TimeSeriesListImpl.fromDoubleList(seriesList.stream()
        .map(doubles -> Arrays.copyOfRange(doubles, start, end + 1))
        .collect(Collectors.toList()));
  }

  @Override
  public final double[][] toDoubleMatrix() {
    int rows = seriesList.size();
    int cols = seriesList.get(0).length;

    //We need to transpose as we go to meet Apache Math convention
    double[][] doubleArray = new double[cols][rows];

    for (int row = 0; row < rows; row++) {
      double[] ts = seriesList.get(row);
      for (int col = 0; col < cols; col++) {
        doubleArray[col][row] = ts[col];
      }
    }
    return doubleArray;
  }

  @Override
  public int getSeriesLength() {
    //TODO: Need to check if list is empty
    return seriesList.get(0).length;
  }

  @Override
  public int getNumberOfSeries() {
    return seriesList.size();
  }

  @Override
  public TimeSeriesList operateOnMatrix(SingleMatrixOperator matrixOperator) {
    double[][] doubles = new double[getNumberOfSeries()][getSeriesLength()];
    doubles = seriesList.toArray(doubles);
    RealMatrix rm = MatrixUtils.createRealMatrix(doubles);
    rm = matrixOperator.operate(rm);
    return TimeSeriesListImpl.fromDoubleArray(rm.getData());
  }
}
