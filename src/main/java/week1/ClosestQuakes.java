package week1;
/**
 * Find N-closest quakes
 *
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {

  public List<QuakeEntry> getClosest(List<QuakeEntry> quakeData, Location current, int howMany) {
    List<QuakeEntry> copy = new ArrayList<>(quakeData);
    List<QuakeEntry> ret = new ArrayList<>();
    int minIndex = 0; // the closest quake seen so far
    for (int j = 0; j < howMany; j++) {
      for (int i = 1; i < copy.size(); i++) {
        QuakeEntry quake = copy.get(i);
        Location location = quake.getLocation();
        if (location.distanceTo(current) < copy.get(minIndex).getLocation().distanceTo(current)) {
          minIndex = i;
        }
      }
      ret.add(copy.get(minIndex));
      copy.remove(minIndex);
    }
    return ret;
  }

  public void findClosestQuakes() {
    EarthQuakeParser parser = new EarthQuakeParser();
    //String source = "data/nov20quakedata.atom";
    String source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/nov20quakedatasmall.atom";
//    String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size());

    Location jakarta = new Location(-6.211, 106.845);

    List<QuakeEntry> close = getClosest(list, jakarta, 3);
    for (int k = 0; k < close.size(); k++) {
      QuakeEntry entry = close.get(k);
      double distanceInMeters = jakarta.distanceTo(entry.getLocation());
      System.out.printf("%4.2f\t %s\n", distanceInMeters / 1000, entry);
    }
    System.out.println("number found: " + close.size());
  }

}
