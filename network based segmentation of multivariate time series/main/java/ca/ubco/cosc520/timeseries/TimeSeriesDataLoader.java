package ca.ubco.cosc520.timeseries;

import lombok.NonNull;

/**
 * Loads a {@link TimeSeriesList} from object of type T. Records should be separated by newlines.
 * Entries should be separated by commas. All entries should be the same length.
 */
public interface TimeSeriesDataLoader<T> {

  TimeSeriesList load(@NonNull T parameter);
}
