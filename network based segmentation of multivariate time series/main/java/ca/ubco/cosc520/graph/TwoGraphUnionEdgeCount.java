package ca.ubco.cosc520.graph;

import lombok.NonNull;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Calculates the untion between two graphs.
 */
public class TwoGraphUnionEdgeCount
    implements TwoGraphOperator<Double> {

  /**
   * Calculates the union between two graphs.
   *
   * @param g The first graph
   * @param h The second graph
   * @return A new graph, which is the union of G and H.
   */
  @Override
  public Double operate(
      @NonNull final Graph g,
      @NonNull final Graph h
  ) {
    RealMatrix rmg = g.getAdjacencyMatrix();
    RealMatrix rmh = h.getAdjacencyMatrix();

    int size = rmg.getColumn(0).length;

    //calculate total number of edges for union graph of g and h
    double edgeNumOfUnion = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        //skip dignose line
        if (j == i) {
          continue;
        }
        if (rmg.getEntry(i, j) == 1 || rmh.getEntry(i, j) == 1) {
          edgeNumOfUnion++;
        }
      }
    }
    edgeNumOfUnion /= 2;

    return edgeNumOfUnion;
  }
}
