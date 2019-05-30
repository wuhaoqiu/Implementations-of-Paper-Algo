package ca.ubco.cosc520.dynamicprogramming;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Interval {

  @Getter
  private final int start;

  @Getter
  private final int end;

  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}
