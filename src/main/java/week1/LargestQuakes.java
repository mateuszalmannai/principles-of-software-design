package week1;

import java.util.*;

public class LargestQuakes {


  public int indexOfLargest(List<QuakeEntry> data) {
    int indexOfLargest = 0;
    double maxMagnitude = 0;
    for (int i = 0; i < data.size(); i++) {
      QuakeEntry quakeEntry = data.get(i);
      if (quakeEntry.getMagnitude() > maxMagnitude) {
        maxMagnitude = quakeEntry.getMagnitude();
        indexOfLargest = i;
      }
    }
    return indexOfLargest;
  }

  public List<QuakeEntry> getLargest(List<QuakeEntry> quakeData, int howMany) {
    List<QuakeEntry> copy = new ArrayList<>(quakeData);
    List<QuakeEntry> result = new ArrayList<>();
    if (quakeData.size() < howMany) {
      howMany = quakeData.size();
    }
    for (int i = 0; i < howMany; i++) {
      int indexOfLargest = indexOfLargest(copy);
      result.add(copy.get(indexOfLargest));
      copy.remove(indexOfLargest);
    }
    return result;
  }

}
