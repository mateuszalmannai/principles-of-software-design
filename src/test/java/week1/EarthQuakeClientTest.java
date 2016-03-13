package week1;

import duke.Location;
import duke.QuakeEntry;
import duke.QuakeTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static week1.EarthQuakeClient.Where.ANY;
import static week1.EarthQuakeClient.Where.END;
import static week1.EarthQuakeClient.Where.START;
import static duke.QuakeTestHelper.Size.LARGE;
import static duke.QuakeTestHelper.Size.SMALL;

public class EarthQuakeClientTest {


  EarthQuakeClient client;

  @Before
  public void setUp() throws Exception {
    client = new EarthQuakeClient();
  }

  @Test
  public void testFilterByPhraseAtEnd() throws Exception {
    List<QuakeEntry> entryList = client.filterByPhrase(QuakeTestHelper.getQuakeEntries(SMALL), END, "California");
    entryList.forEach(System.out::println);
    assertThat(entryList.size(), is(4));
    System.out.println("Found " + entryList.size() + " that match California at end.");
  }

  @Test
  public void testFilterByPhraseAtEndQuiz() throws Exception {
    List<QuakeEntry> entryList = client.filterByPhrase(QuakeTestHelper.getQuakeEntries(LARGE), END, "Alaska");
    entryList.forEach(System.out::println);
    assertThat(entryList.size(), is(364));
    System.out.println("Found " + entryList.size() + " that match Alaska at end.");
  }

  @Test
  public void testFilterByPhraseAnywhere() throws Exception {
    List<QuakeEntry> entryList = client.filterByPhrase(QuakeTestHelper.getQuakeEntries(LARGE), ANY, "Can");
    entryList.forEach(System.out::println);
    assertThat(entryList.size(), is(58));
    System.out.println("Found " + entryList.size() + " that match Can at any.");
  }

  @Test
  public void testFilterByPhraseAnywhereQuiz() throws Exception {
    List<QuakeEntry> entryList = client.filterByPhrase(QuakeTestHelper.getQuakeEntries(LARGE), ANY, "Creek");
    entryList.forEach(System.out::println);
    assertThat(entryList.size(), is(9));
    System.out.println("Found " + entryList.size() + " that match Creek at any.");
  }

  @Test
  public void testFilterByPhraseAtStart() throws Exception {
    List<QuakeEntry> entryList = client.filterByPhrase(QuakeTestHelper.getQuakeEntries(SMALL), START, "Explosion");
    entryList.forEach(System.out::println);
    assertThat(entryList.size(), is(2));
    System.out.println("Found " + entryList.size() + " that match Explosion at start.");
  }

  @Test
  public void testFilterByPhraseAtStartQuiz() throws Exception {
    List<QuakeEntry> entryList = client.filterByPhrase(QuakeTestHelper.getQuakeEntries(LARGE), START, "Quarry Blast");
    entryList.forEach(System.out::println);
    assertThat(entryList.size(), is(19));
    System.out.println("Found " + entryList.size() + " that match Explosion at start.");
  }

  @Test
  public void testFilterByDepth() throws Exception {
    List<QuakeEntry> quakeEntryList = client.filterByDepth(QuakeTestHelper.getQuakeEntries(SMALL), -10000.0, -5000.0);
    quakeEntryList.forEach(System.out::println);
    assertThat(quakeEntryList.size(), is(5));
    System.out.println("Found " + quakeEntryList.size() + " quakes that match that criteria.");
  }

  @Test
  public void testFilterByDepthQuiz() throws Exception {
    List<QuakeEntry> quakeEntryList = client.filterByDepth(QuakeTestHelper.getQuakeEntries(LARGE), -12000.0, -10000.0);
    quakeEntryList.forEach(System.out::println);
    assertThat(quakeEntryList.size(), is(127));
    System.out.println("Found " + quakeEntryList.size() + " quakes that match that criteria.");
  }

  @Test
  public void testFilterByMagnitude() throws Exception {
    List<QuakeEntry> quakeEntryList = client.filterByMagnitude(QuakeTestHelper.getQuakeEntries(SMALL), 5.0);
    quakeEntryList.forEach(System.out::println);
    assertThat(quakeEntryList.size(), is(3));
    System.out.println("Found " + quakeEntryList.size() + " quakes that match that criteria.");
  }

  @Test
  public void testFilterByDistance() throws Exception {
    // This location is Durham, NC
//    Location city = new Location(35.988, -78.907);

    // This location is Bridgeport, CA
    Location city = new Location(38.17, -118.82);
    List<QuakeEntry> list = client.filterByDistanceFrom(QuakeTestHelper.getQuakeEntries(SMALL), 1000000, city);
    list.forEach(System.out::println);
    assertThat(list.size(), is(7));
    System.out.println("Found " + list.size() + " quakes that match criteria.");
  }

  @Test
  public void testCreateCSV() throws Exception {
    client.createCSV();
  }

}