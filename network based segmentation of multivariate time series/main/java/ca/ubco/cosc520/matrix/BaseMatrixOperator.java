package ca.ubco.cosc520.matrix;

import java.util.function.DoubleUnaryOperator;
import lombok.NonNull;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Common lambda to apply operation to each cell in a matrix.
 */
abstract class BaseMatrixOperator {

  /**
   * Performs the operation on the matrix.
   *
   * @param g The first matrix
   * @param operator The operator to be applied to each cell
   * @return The result of the operation.
   */
  RealMatrix operate(@NonNull final RealMatrix g, @NonNull final DoubleUnaryOperator operator) {
    RealMatrix r = g.copy();
    for (int row = 0; row < r.getRowDimension(); row++) {
      for (int col = 0; col < r.getColumnDimension(); col++) {
        r.setEntry(row, col,
            operator.applyAsDouble(r.getEntry(row, col)));
      }
    }
    return r;
  }

}
