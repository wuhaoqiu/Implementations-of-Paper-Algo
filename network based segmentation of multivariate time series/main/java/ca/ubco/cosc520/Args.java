package ca.ubco.cosc520;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.EnumConverter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import lombok.Getter;

public class Args {

  @Parameter(
      names = "-file",
      converter = FileConverter.class,
      required = true,
      description = "The file to process"
  )
  @Getter
  private File file;
  @Parameter(
      names = "-breakpointV",
      description = "V paramter for tuning breakpoint penalty"
  )
  @Getter
  private double breakpointV = 1.0;
  @Parameter(names = "-comparatorV", description = "Value to use when comparing matrices")
  @Getter
  private double comparatorV = 0.01;
  @Parameter(
      converter = BreakpointEnumConverter.class,
      names = "-BreakpointStrategy",
      description = "The strategy to use for calculating breakpoint penalty"
  )
  @Getter
  private BreakpointStrategy breakpointStrategy = Args.BreakpointStrategy.NORMALIZED_EXPONENTIAL;
  @Parameter(
      converter = ComparatorEnumConverter.class,
      names = "-ComparatorStrategy",
      description = "The strategy to use for comparing timeseries"
  )
  @Getter
  private ComparatorStrategy comparatorStrategy = Args.ComparatorStrategy.PVALUE;
  @Parameter(
      converter = DifferenceEnumConverter.class,
      names = "-DifferenceStrategy",
      description = "The strategy to use for calculating difference between graphs"
  )
  @Getter
  private DifferenceStrategy differenceStrategy = Args.DifferenceStrategy.POSSIBLE_EDGE_DENOMINATOR;
  @Parameter(
      names = "-start",
      description = "Truncate the timeseries to start at this value (inclusive) before running."
  )
  @Getter
  private Integer start;
  @Parameter(
      names = "-end",
      description = "Truncate the timeseries to end at this value (inclusive) before running."
  )
  @Getter
  private Integer end;
  @Parameter (
      names = "-shuffle",
      description = "Shuffles the timeseries before applying segmentation algorithm"
  )
  @Getter
  private boolean shuffle;


  public enum BreakpointStrategy {
    LINEAR,
    CONSTANT,
    EXPONENTIAL,
    NORMALIZED_EXPONENTIAL,
    K_SEGMENT
  }

  public enum ComparatorStrategy {
    CORRELATION,
    PVALUE
  }


  public enum DifferenceStrategy {
    UNION_DENOMINATOR,
    POSSIBLE_EDGE_DENOMINATOR
  }

  private static class BreakpointEnumConverter extends EnumConverter<BreakpointStrategy> {

    /**
     * Constructs a new converter.
     *
     * @param optionName the option name for error reporting
     * @param clazz the enum class
     */
    public BreakpointEnumConverter(String optionName,
        Class<BreakpointStrategy> clazz) {
      super(optionName, clazz);
    }
  }

  private static class ComparatorEnumConverter extends EnumConverter<ComparatorStrategy> {

    /**
     * Constructs a new converter.
     *
     * @param optionName the option name for error reporting
     * @param clazz the enum class
     */
    public ComparatorEnumConverter(String optionName,
        Class<ComparatorStrategy> clazz) {
      super(optionName, clazz);
    }
  }

  private static class DifferenceEnumConverter extends EnumConverter<DifferenceStrategy> {

    /**
     * Constructs a new converter.
     *
     * @param optionName the option name for error reporting
     * @param clazz the enum class
     */
    public DifferenceEnumConverter(String optionName,
        Class<DifferenceStrategy> clazz) {
      super(optionName, clazz);
    }
  }

}
