package ca.ubco.cosc520;

import ca.ubco.cosc520.dynamicprogramming.Interval;
import java.util.List;
import lombok.NonNull;

public class IntervalListToCsv {

  public static String fromInterval(@NonNull List<Interval> intervalList) {
    StringBuilder sb = new StringBuilder("0,");
    intervalList.stream().forEach(interval -> {
      sb.append(interval.getEnd());
      sb.append(",");
    });
    return sb.deleteCharAt(sb.length() - 1).toString();
  }

}
