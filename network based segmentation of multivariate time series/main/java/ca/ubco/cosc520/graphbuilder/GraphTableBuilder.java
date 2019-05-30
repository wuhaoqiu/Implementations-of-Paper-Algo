package ca.ubco.cosc520.graphbuilder;

import ca.ubco.cosc520.graph.Graph;
import ca.ubco.cosc520.timeseries.TimeSeriesList;
import lombok.NonNull;

/**
 * Iterates over all possible {@code start} and {@code end} combinations in a {@link TimeSeriesList}
 * and produces the resulting table of {@link Graph} objects.
 */
public class GraphTableBuilder {

  private final GraphBuilder graphBuilder;

  public GraphTableBuilder(GraphBuilder graphBuilder) {
    this.graphBuilder = graphBuilder;
  }

  /**
   * Produce the {@link Graph} table using a default {@code minLength}.
   *
   * @param timeSeriesList The {@link TimeSeriesList} to be turned into a Graph table.
   * @return The {@link Graph} table.
   */
  public Graph[][] tableFromTimeSeriesList(@NonNull TimeSeriesList timeSeriesList) {
    return this.tableFromTimeSeriesList(timeSeriesList, 2);
  }

  /**
   * Produce the {@link Graph} table using.
   *
   * @param timeSeriesList The {@link TimeSeriesList} to be turned into a Graph table.
   * @param minLength The minimum length of the {@link TimeSeriesList} to be processed. {@code
   * start} and {@code end} combinations producing entries less than this length result in an empty
   * Graph.
   * @return The {@link Graph} table.
   */
  public Graph[][] tableFromTimeSeriesList(@NonNull TimeSeriesList timeSeriesList, int minLength) {

    int timePoints = timeSeriesList.getSeriesLength();

    Graph[][] graphs = new Graph[timePoints][timePoints];

    for (int start = 0; start < timePoints; start++) {
      for (int end = start; end < timePoints; end++) {
        if (end - start > minLength) {
          graphs[start][end] = graphBuilder.makeGraph(timeSeriesList.truncate(start, end));
        }
      }
    }
    return graphs;
  }
}
