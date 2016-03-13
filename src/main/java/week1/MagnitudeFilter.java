package week1;

import duke.QuakeEntry;

public class MagnitudeFilter implements Filter {

  private double minimumMagnitude;
  private double maximumMagnitude;
  private String filterName;

  public MagnitudeFilter(double minimumMagnitude, double maximumMagnitude, String filterName) {
    this.minimumMagnitude = minimumMagnitude;
    this.maximumMagnitude = maximumMagnitude;
    this.filterName = filterName;
  }

  public MagnitudeFilter(double minimumMagnitude, double maximumMagnitude) {
    this.minimumMagnitude = minimumMagnitude;
    this.maximumMagnitude = maximumMagnitude;
  }

  @Override
  public boolean satisfies(QuakeEntry qe) {
    double magnitude = qe.getMagnitude();
    return magnitude >= minimumMagnitude && magnitude <= maximumMagnitude;
  }

  @Override
  public String getName() {
    return filterName;
  }
}
