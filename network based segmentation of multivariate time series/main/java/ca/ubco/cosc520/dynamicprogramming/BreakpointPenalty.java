package ca.ubco.cosc520.dynamicprogramming;

public interface BreakpointPenalty {

  double getPenalty(int currentCuts, int maximumPossibleCuts);
}
