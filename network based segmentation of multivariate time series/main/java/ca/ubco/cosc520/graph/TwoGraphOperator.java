package ca.ubco.cosc520.graph;

import lombok.NonNull;

/**
 * Operations which can be performed between 2 {@link Graph}s.
 *
 * @param <T> - The return type of the operation.
 */
public interface TwoGraphOperator<T> {

  /**
   * Performs the operation on the two graphs.
   *
   * @param g The first graph
   * @param h The second graph
   * @return The result of the operation.
   */
  T operate(@NonNull Graph g, @NonNull Graph h);

}
