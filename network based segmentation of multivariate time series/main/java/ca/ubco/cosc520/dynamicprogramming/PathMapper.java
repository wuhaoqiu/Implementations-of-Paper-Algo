package ca.ubco.cosc520.dynamicprogramming;

import ca.ubco.cosc520.graph.Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;

/**
 * Produces the Dynamic Programming Path.
 */
public class PathMapper {

  private static final int MIN_LENGTH = 5;
  private final BreakpointPenalty breakpointPenalty;

  public PathMapper(@NonNull BreakpointPenalty breakpointPenalty) {
    this.breakpointPenalty = breakpointPenalty;
  }

  /**
   * Performs the dynamic programming algorithm on a table of {@link Graph} objects.
   *
   * @param cutValues Three dimensional array representing cutValues. First element in array
   * indicates start of first interval Second element in array indicates cut point (end of first
   * interval, 1 before start of second) Thirs element in array indicates end of second interval
   * @return A {@link List} of {@link Integer}s representing the position of the splits.
   */
  public List<Interval> dynamicProgramming(double[][][] cutValues) {

    int numPoints = cutValues.length;

    Table dptable = new Table(numPoints);

    for (int cut = MIN_LENGTH; cut < numPoints; cut++) {
      for (int end = cut + MIN_LENGTH; end < numPoints; end++) {

        Step currentStep = dptable.get(cut, end);

        for (int start = 0; start < cut; start++) {
          Step step = dptable.get(start, cut);

          double newVal = step.getValue() + cutValues[start + 1][cut][end]
              - breakpointPenalty.getPenalty(step.getPath().size() + 1, numPoints / MIN_LENGTH);

          // if better value can be found with a better segmentation
          if (newVal > step.getValue()) {
            currentStep.setValue(newVal);
            List<Interval> newIntervalPath = new ArrayList<>(step.getPath());
            newIntervalPath.add(new Interval(cut+1, end));
            currentStep.setPath(newIntervalPath);
          }
        }
      }

    }

    double maxVal = Double.MIN_VALUE;
    List<Interval> bestPath = Collections.singletonList(new Interval(0, numPoints - 1));
    for (int cut = MIN_LENGTH; cut < numPoints - MIN_LENGTH; cut++) {
      Step curStep = dptable.get(cut, numPoints - 1);

      if (curStep.getValue() > maxVal) {
        bestPath = curStep.getPath();
      }

    }

    return bestPath;
  }


}
