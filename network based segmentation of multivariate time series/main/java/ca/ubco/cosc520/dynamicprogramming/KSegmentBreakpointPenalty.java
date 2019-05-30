package ca.ubco.cosc520.dynamicprogramming;

public class KSegmentBreakpointPenalty implements BreakpointPenalty {

  private final double tuningParamter;

  public KSegmentBreakpointPenalty(double tuningParamter) {
    this.tuningParamter = tuningParamter;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
    if (currentCuts > tuningParamter) {
      return Double.MAX_VALUE;
    } else {
      return 0;
    }
  }

}
