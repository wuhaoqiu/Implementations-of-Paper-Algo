package ca.ubco.cosc520.matrix;

import lombok.NonNull;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Returns a graph with the absolute value of each value in {@code g}.
 */
public class MatrixAbsoluteValue
    extends BaseMatrixOperator implements SingleMatrixOperator {

  /**
   * Performs the operation on the matrix and returns the result.
   *
   * @param g The matrix
   * @return A matrix with the values replaced by absolute values.
   */
  @Override
  public RealMatrix operate(@NonNull final RealMatrix g) {
    return this.operate(g, Math::abs);
  }
}
