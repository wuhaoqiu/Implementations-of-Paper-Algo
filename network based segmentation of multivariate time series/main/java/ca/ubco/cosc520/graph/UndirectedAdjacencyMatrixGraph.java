package ca.ubco.cosc520.graph;

import lombok.NonNull;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * A graph implementation representated as an adjacency matrix.
 */
public class UndirectedAdjacencyMatrixGraph implements Graph {

  /**
   * Internal representation of graph - Adjacency Matrix.
   */
  private final RealMatrix adjacencyMatrix;

  public UndirectedAdjacencyMatrixGraph(@NonNull double[][] doubles) {
    this(MatrixUtils.createRealMatrix(doubles));
  }

  /**
   * Create the graph from a square inputMatrix of values {0,1} 0 - represents no edge between
   * nodes. 1 - represents edge between nodes.
   *
   * @param inputMatrix The adjacency matrix representation.
   */
  public UndirectedAdjacencyMatrixGraph(@NonNull final RealMatrix inputMatrix) {

    if (inputMatrix.getRowDimension() != inputMatrix.getColumnDimension()) {
      throw new IllegalArgumentException("Input matrix must be square");
    }

    for (int row = 0; row < inputMatrix.getRowDimension(); row++) {
      for (int col = 0; col < inputMatrix.getColumnDimension(); col++) {
        if (row == col && inputMatrix.getEntry(row, col) != 1d) {
          throw new IllegalArgumentException(
              "Diagonals of the matrix must be 1."
          );
        }
        if (inputMatrix.getEntry(row, col) != 0d
            && inputMatrix.getEntry(row, col) != 1d) {
          throw new IllegalArgumentException(
              "Input matrix must contain only 0s or 1s"
          );
        }

        if (inputMatrix.getEntry(row, col) != inputMatrix.getEntry(col, row)) {
          throw new IllegalArgumentException(
              "Input matrix must be symmetric."
          );
        }
      }
    }

    this.adjacencyMatrix = inputMatrix;
  }

  @Override
  public RealMatrix getAdjacencyMatrix() {
    return adjacencyMatrix;
  }

}
