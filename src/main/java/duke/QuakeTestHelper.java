package duke;

import java.util.List;

public class QuakeTestHelper {

  public enum Size {LARGE, SMALL, SAMPLE_SIX1, SAMPLE_SIX2, DEC6_SAMPLE1, DEC6_SAMPLE2}

  public static List<QuakeEntry> getQuakeEntries(Size size) {
    EarthQuakeParser parser = new EarthQuakeParser();
//    String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    String source;

    switch (size) {
      case SMALL:
        source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/nov20quakedatasmall.atom";
        break;
      case LARGE:
        source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/nov20quakedata.atom";
        break;
      case SAMPLE_SIX1:
        source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/earthquakeDataSampleSix1.atom";
        break;
      case SAMPLE_SIX2:
        source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/earthquakeDataSampleSix2.atom";
        break;
      case DEC6_SAMPLE1:
        source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/earthQuakeDataWeekDec6sample1.atom";
        break;
      case DEC6_SAMPLE2:
        source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/earthQuakeDataWeekDec6sample2.atom";
        break;
      default:
        throw new UnsupportedOperationException("Shouldn't ever get here");
    }


    List<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    return list;
  }
}
