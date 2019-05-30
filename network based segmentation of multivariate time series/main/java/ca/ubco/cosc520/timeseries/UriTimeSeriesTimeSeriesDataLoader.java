package ca.ubco.cosc520.timeseries;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;

/**
 * Loads a {@link TimeSeriesList} from a URI. Records should be separated by newlines. Entries
 * should be separated by commas. All entries should be the same length.
 */
public class UriTimeSeriesTimeSeriesDataLoader implements TimeSeriesDataLoader<URI> {

  private static final String NEWLINE_SEPARATOR = "\n";
  private static final String ENTRY_SEPARATOR = ",";

  /**
   * Loads the data from the file.
   *
   * @param uri The {@link URI} to load from.
   * @return The populated {@link TimeSeriesList}
   */
  public TimeSeriesList load(@NonNull final URI uri) {

    String data;
    try {
      Path path;
      path = Paths.get(Objects.requireNonNull(uri));

      Stream<String> lines = Files.lines(path);
      data = lines.collect(Collectors.joining(NEWLINE_SEPARATOR));
      lines.close();

    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    List<double[]> allTimeSeries = new ArrayList<>();

    for (String line : data.split(NEWLINE_SEPARATOR)) {

      String[] stringValues = line.split(ENTRY_SEPARATOR);
      double[] doubles = new double[stringValues.length];
      for (int i = 0; i < stringValues.length; i++) {
        doubles[i] = Double.parseDouble(stringValues[i]);
      }
      allTimeSeries.add(doubles);
    }
    TimeSeriesList timeSeriesList = new TimeSeriesListImpl();
    for (double[] doubles : allTimeSeries) {
      timeSeriesList.add(doubles);
    }
    return timeSeriesList;
  }
}
