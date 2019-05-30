package ca.ubco.cosc520.dynamicprogramming;

public class ConstantBreakpointPenalty implements BreakpointPenalty {

  private final double tuningParamter;

  public ConstantBreakpointPenalty(double tuningParamter) {
    this.tuningParamter = tuningParamter;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
    return tuningParamter;
  }

}
