package ca.ubco.cosc520.matrix;

import lombok.NonNull;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Produces a new graph with thresholding applied.
 */
public class MatrixLessThanThresholder
    extends BaseMatrixOperator implements SingleMatrixOperator {

  /**
   * The threshold to compare against.
   */
  private final double thresh;

  /**
   * Initializes a new matrix thresholder with the threshold configured.
   *
   * @param threshold The threshold to compare against.
   */
  public MatrixLessThanThresholder(final double threshold) {
    this.thresh = threshold;
  }

  /**
   * Performs the thresholding and returns the resulting matrix.
   *
   * @param g The matrix
   * @return A matrix with each cell replaced: 1 if the value of the cell is >= the threshold, 0
   * otherwise.
   */
  @Override
  public RealMatrix operate(@NonNull final RealMatrix g) {
    return this.operate(g, (double d) -> {
      if (d <= thresh) {
        return 1d;
      } else {
        return 0d;
      }
    });
  }
}
