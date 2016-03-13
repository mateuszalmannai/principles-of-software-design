package duke;

import java.util.Collections;
import java.util.Comparator;

public class DistanceComparator implements Comparator<QuakeEntry> {

  private Location fromWhere;

  public DistanceComparator(Location fromWhere) {
    this.fromWhere = fromWhere;
  }

  @Override
  public int compare(QuakeEntry q1, QuakeEntry q2) {
    double dist1 = q1.getLocation().distanceTo(fromWhere);
    double dist2 = q2.getLocation().distanceTo(fromWhere);

    return Double.compare(dist1, dist2);
  }
}
