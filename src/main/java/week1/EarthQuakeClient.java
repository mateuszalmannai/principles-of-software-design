package week1;

import duke.EarthQuakeParser;
import duke.Location;
import duke.QuakeEntry;

import java.util.*;

public class EarthQuakeClient {

  enum Where {
    START("start"), END("end"), ANY("any");

    private String where;

    Where(String where) {
      this.where = where;
    }
  }

  public EarthQuakeClient() {
    // TODO Auto-generated constructor stub
  }

  public List<QuakeEntry> filterByMagnitude(List<QuakeEntry> quakeData, double magMin) {
    List<QuakeEntry> answer = new ArrayList<>();
    for (QuakeEntry entry : quakeData) {
      if (entry.getMagnitude() > magMin) {
        answer.add(entry);
      }
    }
    return answer;
  }

  public List<QuakeEntry> filterByDistanceFrom(List<QuakeEntry> quakeData, double distMax, Location from) {
    List<QuakeEntry> answer = new ArrayList<>();
    for (QuakeEntry entry : quakeData) {
      if (entry.getLocation().distanceTo(from) < distMax) {
        answer.add(entry);
      }
    }
    return answer;
  }

  public List<QuakeEntry> filterByDepth(List<QuakeEntry> quakeData, double minDepth, double maxDepth) {
    List<QuakeEntry> result = new ArrayList<>();
    for (QuakeEntry entry : quakeData) {
      double entryDepth = entry.getDepth();
      if (entryDepth > minDepth && entryDepth < maxDepth) {
        result.add(entry);
      }
    }
    return result;
  }

  public List<QuakeEntry> filterByPhrase(List<QuakeEntry> quakeData, Where where, String phrase) {
    List<QuakeEntry> result = new ArrayList<>();
    boolean condition;
    for (QuakeEntry quakeEntry : quakeData) {
      String info = quakeEntry.getInfo();
      switch (where) {
        case START:
          condition = info.startsWith(phrase);
          break;
        case END:
          condition = info.endsWith(phrase);
          break;
        default:
          condition = info.contains(phrase);
      }
      if (condition) {
        result.add(quakeEntry);
      }
    }
    return result;
  }

  public void dumpCSV(List<QuakeEntry> list) {
    System.out.println("Latitude,Longitude,Magnitude,Info");
    for (QuakeEntry qe : list) {
      System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
        qe.getLocation().getLatitude(),
        qe.getLocation().getLongitude(),
        qe.getMagnitude(),
        qe.getInfo());
    }

  }

  public void createCSV() {
    EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "data/nov20quakedatasmall.atom";
    String source = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/nov20quakedata.atom";
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    List<QuakeEntry> list = parser.read(source);
    dumpCSV(list);
    System.out.println("# quakes read: " + list.size());
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
  }

}
