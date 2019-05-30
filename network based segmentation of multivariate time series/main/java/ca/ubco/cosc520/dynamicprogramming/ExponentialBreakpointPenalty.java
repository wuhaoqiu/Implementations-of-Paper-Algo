package ca.ubco.cosc520.dynamicprogramming;

public class ExponentialBreakpointPenalty implements BreakpointPenalty {

  private final double tuningParameter;

  public ExponentialBreakpointPenalty(double tuningParameter) {
    this.tuningParameter = tuningParameter;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
    return Math.exp(tuningParameter * currentCuts / maximumPossibleCuts);
  }

}
