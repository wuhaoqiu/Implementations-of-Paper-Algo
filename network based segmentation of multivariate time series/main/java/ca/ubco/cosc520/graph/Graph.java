package ca.ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * A graph class.
 */
public interface Graph {

  /**
   * Gives the adjacency matrix representation of the graph.
   *
   * @return The adjacency matrix representation of the graph.
   */
  RealMatrix getAdjacencyMatrix();
}
