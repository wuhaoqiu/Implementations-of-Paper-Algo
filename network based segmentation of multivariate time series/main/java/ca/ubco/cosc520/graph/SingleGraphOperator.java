package ca.ubco.cosc520.graph;

import lombok.NonNull;

/**
 * Operations which can be performed on a single {@link Graph}.
 *
 * @param <T> - The return type of the operation.
 */
public interface SingleGraphOperator<T> {

  /**
   * Performs the operation on the two graphs.
   *
   * @param g The  graph
   * @return The result of the operation.
   */
  T operate(@NonNull Graph g);

}
