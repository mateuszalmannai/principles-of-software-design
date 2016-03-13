package week1;

import duke.EarthQuakeParser;
import duke.QuakeEntry;

import java.util.*;

public class EarthQuakeClient2 {
  public EarthQuakeClient2() {
    // TODO Auto-generated constructor stub
  }

  public List<QuakeEntry> filter(List<QuakeEntry> quakeData, Filter f) {
    List<QuakeEntry> answer = new ArrayList<>();
    for (QuakeEntry qe : quakeData) {
      if (f.satisfies(qe)) {
        answer.add(qe);
      }
    }

    return answer;
  }

  public void quakesWithFilter() {
    EarthQuakeParser parser = new EarthQuakeParser();
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    String source = "data/nov20quakedatasmall.atom";
    List<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");

    MagnitudeFilter magnitudeFilter = new MagnitudeFilter(4.0, 5.0);


    Filter f = new MinMagFilter(4.0, "MinMagFilter");
    List<QuakeEntry> m7 = filter(list, f);
    for (QuakeEntry qe : m7) {
      System.out.println(qe);
    }
  }

  public void createCSV() {
    EarthQuakeParser parser = new EarthQuakeParser();
    //String source = "../data/nov20quakedata.atom";
    String source = "data/nov20quakedatasmall.atom";
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    List<QuakeEntry> list = parser.read(source);
    dumpCSV(list);
    System.out.println("# quakes read: " + list.size());
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

}
