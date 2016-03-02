package week1;

import java.util.List;

public class QuakeTestHelper {

  public enum Size {LARGE, SMALL;}

  public static List<QuakeEntry> getQuakeEntries(Size size) {
    EarthQuakeParser parser = new EarthQuakeParser();
//    String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    String source;
    if (size == Size.SMALL) {
      source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/nov20quakedatasmall.atom";
    } else {
      source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/nov20quakedata.atom";
    }
    List<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    return list;
  }
}
