package ca.ubco.cosc520.timeseries;

import java.net.URI;
import java.net.URISyntaxException;
import lombok.NonNull;
import lombok.extern.java.Log;

/**
 * Loads a {@link TimeSeriesList} from a csv-formatted entry. Records should be separated by
 * newlines. Entries should be separated by commas. All entries should be the same length.
 */
@Log
public class ClasspathFileTimeSeriesDataLoader implements TimeSeriesDataLoader<String> {

  public TimeSeriesList load(@NonNull String filename) {

    try {
      URI uri = ClasspathFileTimeSeriesDataLoader.class.getClassLoader()
          .getResource(filename).toURI();
      return new UriTimeSeriesTimeSeriesDataLoader().load(uri);
    } catch (URISyntaxException e) {
      log.severe(e.getMessage());
      throw new RuntimeException(e);
    }

  }
}
