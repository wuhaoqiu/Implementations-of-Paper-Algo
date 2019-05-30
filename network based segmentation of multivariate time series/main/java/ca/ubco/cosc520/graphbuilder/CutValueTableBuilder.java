package ca.ubco.cosc520.graphbuilder;

import ca.ubco.cosc520.graph.Graph;
import ca.ubco.cosc520.graph.TwoGraphOperator;
import lombok.NonNull;

public class CutValueTableBuilder {

  private static final int MIN_LENGTH = 5;
  private final TwoGraphOperator<Double> distanceCalculator;

  public CutValueTableBuilder(final TwoGraphOperator<Double> distanceCalculator) {
    this.distanceCalculator = distanceCalculator;
  }

  public double[][][] getCutValues(@NonNull final Graph[][] graphs) {
    int numPoints = graphs[0].length;

    double[][][] cutValues = new double[numPoints][numPoints][numPoints];

    for (int start = 0; start < numPoints - MIN_LENGTH * 2; start++) {
      for (int cut = start + MIN_LENGTH - 1; cut < numPoints - MIN_LENGTH; cut++) {
        for (int end = cut + MIN_LENGTH; end < numPoints; end++) {
          Graph g1 = graphs[start][cut];
          Graph g2 = graphs[cut + 1][end];
          cutValues[start][cut][end] = distanceCalculator.operate(g1, g2);
        }
      }
    }

    return cutValues;
  }
}
