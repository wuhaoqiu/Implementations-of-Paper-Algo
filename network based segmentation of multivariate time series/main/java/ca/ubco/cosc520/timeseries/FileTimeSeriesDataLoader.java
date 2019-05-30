package ca.ubco.cosc520.timeseries;

import java.io.File;
import lombok.NonNull;
import lombok.extern.java.Log;

/**
 * Loads a {@link TimeSeriesList} from a csv-formatted entry. Records should be separated by
 * newlines. Entries should be separated by commas. All entries should be the same length.
 */
@Log
public class FileTimeSeriesDataLoader implements TimeSeriesDataLoader<File> {

  public TimeSeriesList load(@NonNull File file) {
    return new UriTimeSeriesTimeSeriesDataLoader().load(file.toURI());
  }
}
