package duke;

import java.util.Comparator;

public class MagnitudeComparator implements Comparator<QuakeEntry>{

  @Override
  public int compare(QuakeEntry qe1, QuakeEntry qe2) {
    return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
  }
}
