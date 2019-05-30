package ca.ubco.cosc520.dynamicprogramming;

public class LinearBreakpointPenalty implements BreakpointPenalty {

  private final double tuningParameter;

  public LinearBreakpointPenalty(double tuningParameter) {
    this.tuningParameter = tuningParameter;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
    return tuningParameter * currentCuts / maximumPossibleCuts;
  }

}
