package ca.ubco.cosc520.matrix;

import lombok.NonNull;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Operation which can be performed on a matrix.
 */
public interface SingleMatrixOperator {

  /**
   * Perform the operation on the matrix.
   *
   * @param g The matrix for the operation.
   * @return A matrix with the operation applied.
   */
  RealMatrix operate(@NonNull RealMatrix g);

}
