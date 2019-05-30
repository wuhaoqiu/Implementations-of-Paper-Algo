package ca.ubco.cosc520.graph;

import lombok.NonNull;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.CombinatoricsUtils;

/**
 * Calculates the symmetric difference between two Graphs.
 */
public class GraphPossibleEdgeCount
    implements SingleGraphOperator<Double> {

  /**
   * Calculates the symmetric difference between two Graphs.
   *
   * @param g The first graph
   * @return A new graph, which is the symmetric difference of G and H.
   */
  @Override
  public Double operate(
      @NonNull final Graph g
  ) {
    RealMatrix rmg = g.getAdjacencyMatrix();

    int numberOfNodes = rmg.getColumn(0).length;
    return (double) CombinatoricsUtils.binomialCoefficient(numberOfNodes, 2);
  }
}
