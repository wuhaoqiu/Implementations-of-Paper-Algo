package ca.ubco.cosc520.timeseries;

import ca.ubco.cosc520.matrix.SingleMatrixOperator;
import java.util.List;

/**
 * A list of multiple time series data. All of the time series must be of the same length. Time
 * series data is expected and returned as an array of doubles.
 */
public interface TimeSeriesList {

  /**
   * Add another time series to the list.
   *
   * @param ts The time series to add.
   */
  void add(double[] ts);

  /**
   * Retrieve the time series by index.
   *
   * @param idx Index of the time series to be retrieved.
   * @return The time series.
   */
  double[] getByIndex(int idx);

  /**
   * Get the full list of time series.
   *
   * @return The time series list.
   */
  List<double[]> getList();

  /**
   * Truncate every time series in the list to be between {@code start} and {@code end}.
   *
   * @param start Integer index to start at (inclusive)
   * @param end Integer index to end at (inclusive)
   * @return A new TimeSeriesList object with truncated timeseries.
   */
  TimeSeriesList truncate(int start, int end);

  /**
   * Transposes and returns the time series list as a two-dimensional array. Suitable for use with
   * the Apache Math3 package.
   *
   * @return The transposed time series list as a two-dimensional array.
   */
  double[][] toDoubleMatrix();

  /**
   * @return the number of points in the timeseries
   */
  int getSeriesLength();

  /**
   * @return the number of timeseries in the list
   */
  int getNumberOfSeries();

  /**
   * Applies the {@link SingleMatrixOperator} on the underlying matrix.
   * @return A new {@link TimeSeriesList} with the matrix operator applied.
   */
  TimeSeriesList operateOnMatrix(SingleMatrixOperator matrixOperator);
}
