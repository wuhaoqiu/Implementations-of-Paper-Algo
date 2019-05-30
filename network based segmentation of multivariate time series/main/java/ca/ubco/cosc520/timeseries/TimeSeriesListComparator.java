package ca.ubco.cosc520.timeseries;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Compares time series and produces a similarity matrix.
 */
public interface TimeSeriesListComparator {

  /**
   * Perform the comparison.
   *
   * @param timeSeriesList The time series list to compare.
   * @return A RealMatrix of
   */
  RealMatrix compare(TimeSeriesList timeSeriesList);

}
