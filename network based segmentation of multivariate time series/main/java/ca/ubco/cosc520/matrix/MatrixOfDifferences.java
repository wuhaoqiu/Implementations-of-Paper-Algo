package ca.ubco.cosc520.matrix;

import lombok.NonNull;
import org.apache.commons.math3.linear.RealMatrix;

public class MatrixOfDifferences implements SingleMatrixOperator {


  @Override
  public RealMatrix operate(@NonNull RealMatrix g) {
    int numOfRow = g.getRowDimension();
    int numOfColumn = g.getColumnDimension();
    RealMatrix differenceMatrix = g.createMatrix(numOfRow, numOfColumn - 1);

    for (int i = 0; i < numOfColumn - 1; i++) {
      double[] columnD = new double[numOfRow];
      double[] current = g.getColumn(i);
      double[] next = g.getColumn(i + 1);
      for (int j = 0; j < current.length; j++) {
        columnD[j] = next[j] - current[j];
      }
      differenceMatrix.setColumn(i, columnD);
    }

    return differenceMatrix;
  }
}
