package week1;

import duke.Location;
import duke.QuakeEntry;

public class DistanceFilter implements Filter {

  private Location locationFrom;
  private double maxDistance;
  private String filterName;

  public DistanceFilter(Location locationFrom, double maxDistance, String filterName) {
    this.locationFrom = locationFrom;
    this.maxDistance = maxDistance;
    this.filterName = filterName;
  }

  public DistanceFilter(Location locationFrom, double maxDistance) {
    this.locationFrom = locationFrom;
    this.maxDistance = maxDistance;
  }

  @Override
  public boolean satisfies(QuakeEntry qe) {
    return qe.getLocation().distanceTo(locationFrom) < maxDistance;
  }

  @Override
  public String getName() {
    return filterName;
  }
}
