package ca.ubco.cosc520.graph;

import lombok.NonNull;

/**
 * Calculates the distance between two graphs.
 */
public class TwoGraphModifiedDistance
    implements TwoGraphOperator<Double> {

  /**
   * Calculates the distance between two graphs.
   *
   * @param g The first graph
   * @param h The second graph
   * @return The distance between two graphs.
   */
  @Override
  public Double operate(
      @NonNull final Graph g,
      @NonNull final Graph h
  ) {
    Double symDiffEdgeCount = new TwoGraphSymDiffEdgeCount().operate(g, h);
    Double possibleEdgeCount = new GraphPossibleEdgeCount().operate(g);

    return symDiffEdgeCount / possibleEdgeCount;

  }
}
