package ca.ubco.cosc520.dynamicprogramming;

public class Table {

  private final Step[][] steps;

  public Table(int size) {
    // Dynamic programming table stores Step(w), the maximum value up to w
    steps = new Step[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = i; j < size; j++) {
        steps[i][j] = new Step(0, i, j);
      }
    }
  }

  public Step get(int cut, int end) {
    return steps[cut][end];
  }

}
