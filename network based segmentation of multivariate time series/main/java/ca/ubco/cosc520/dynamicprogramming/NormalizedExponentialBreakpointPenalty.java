package ca.ubco.cosc520.dynamicprogramming;

public class NormalizedExponentialBreakpointPenalty extends ExponentialBreakpointPenalty {

  private final double tuningParameter;

  public NormalizedExponentialBreakpointPenalty(double tuningParameter) {
    super(tuningParameter);
    this.tuningParameter = tuningParameter;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
    return super.getPenalty(currentCuts, maximumPossibleCuts) - 1;
  }

}
