package duke;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

  @Override
  public int compare(QuakeEntry o1, QuakeEntry o2) {
    String o1Info = o1.getInfo();
    String o1LastWord = o1Info.substring(o1Info.lastIndexOf(" ") + 1);
    String o2Info = o2.getInfo();
    String o2LastWord = o2Info.substring(o2Info.lastIndexOf(" ") + 1);
    int lastWordResult = o1LastWord.compareTo(o2LastWord);
    return lastWordResult != 0 ? lastWordResult : Double.compare(o1.getMagnitude(), o2.getMagnitude());
  }
}
