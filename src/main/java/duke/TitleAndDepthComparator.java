package duke;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {


  @Override
  public int compare(QuakeEntry q1, QuakeEntry q2) {
    int titleResult = q1.getInfo().compareTo(q2.getInfo());
    return titleResult != 0 ? titleResult : Double.compare(q1.getDepth(), q2.getDepth());
  }
}
