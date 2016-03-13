package week1;

import duke.Location;
import duke.QuakeEntry;
import duke.QuakeTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static week1.EarthQuakeClient.Where.ANY;
import static week1.EarthQuakeClient.Where.END;
import static duke.QuakeTestHelper.Size.LARGE;
import static duke.QuakeTestHelper.Size.SMALL;

public class EarthQuakeClient2Test {

  EarthQuakeClient2 client;


  @Before
  public void setUp() throws Exception {
    client = new EarthQuakeClient2();
  }

  @Test
  public void testFilterUsingTwoCriteria() throws Exception {
    MagnitudeFilter magnitudeFilter = new MagnitudeFilter(4.0, 5.0);
    DepthFilter depthFilter = new DepthFilter(-35000.0, -12000.0);
    List<QuakeEntry> filteredByMagnitude = client.filter(QuakeTestHelper.getQuakeEntries(SMALL), magnitudeFilter);
    List<QuakeEntry> filteredByDepth = client.filter(filteredByMagnitude, depthFilter);
//    filteredByDepth.forEach(System.out::println);
    assertThat(filteredByDepth.size(), is(2));
  }

  @Test
  public void testFilterUsingTwoCriteriaQuiz() throws Exception {
    MagnitudeFilter magnitudeFilter = new MagnitudeFilter(3.5, 4.5);
    DepthFilter depthFilter = new DepthFilter(-55000.0, -20000.0);
    List<QuakeEntry> filteredByMagnitude = client.filter(QuakeTestHelper.getQuakeEntries(LARGE), magnitudeFilter);
    List<QuakeEntry> filteredByDepth = client.filter(filteredByMagnitude, depthFilter);
    filteredByDepth.forEach(System.out::println);
    assertThat(filteredByDepth.size(), is(15));
  }

  @Test
  public void testFilterUsingTwoCriteria2() throws Exception {
    DistanceFilter distanceFilter = new DistanceFilter(new Location(35.42, 139.43), 10000000);
    PhraseFilter phraseFilter = new PhraseFilter("Japan", END);
    List<QuakeEntry> filteredByDistance = client.filter(QuakeTestHelper.getQuakeEntries(SMALL), distanceFilter);
    List<QuakeEntry> filteredByPhrase = client.filter(filteredByDistance, phraseFilter);
//    filteredByPhrase.forEach(System.out::println);
    assertThat(filteredByPhrase.size(), is(2));
  }

  @Test
  public void testFilterUsingTwoCriteria2Quiz2() throws Exception {
    DistanceFilter distanceFilter = new DistanceFilter(new Location(39.7392, -104.9903), 1000000);
    PhraseFilter phraseFilter = new PhraseFilter("a", END);
    List<QuakeEntry> filteredByDistance = client.filter(QuakeTestHelper.getQuakeEntries(LARGE), distanceFilter);
    List<QuakeEntry> filteredByPhrase = client.filter(filteredByDistance, phraseFilter);
    filteredByPhrase.forEach(System.out::println);
    assertThat(filteredByPhrase.size(), is(74));
  }

  @Test
  public void testFilterUsingTwoCriteria2Quiz() throws Exception {
    DistanceFilter distanceFilter = new DistanceFilter(new Location(35.42, 139.43), 10000000);
    PhraseFilter phraseFilter = new PhraseFilter("Japan", END);
    List<QuakeEntry> filteredByDistance = client.filter(QuakeTestHelper.getQuakeEntries(LARGE), distanceFilter);
    List<QuakeEntry> filteredByPhrase = client.filter(filteredByDistance, phraseFilter);
    filteredByPhrase.forEach(System.out::println);
    assertThat(filteredByPhrase.size(), is(20));
  }

  @Test
  public void testMatchAllFilterThreeCriteriaQuiz() throws Exception {
    MagnitudeFilter magnitudeFilter = new MagnitudeFilter(1.0, 4.0);
    DepthFilter depthFilter = new DepthFilter(-180000.0, -30000.0);
    PhraseFilter phraseFilter = new PhraseFilter("o", ANY);
    MatchAllFilter matchAllFilter = new MatchAllFilter();
    matchAllFilter.addFilter(magnitudeFilter);
    matchAllFilter.addFilter(depthFilter);
    matchAllFilter.addFilter(phraseFilter);
    List<QuakeEntry> filteredList = client.filter(QuakeTestHelper.getQuakeEntries(LARGE), matchAllFilter);
    filteredList.forEach(System.out::println);
    assertThat(filteredList.size(), is(187));
  }

  @Test
  public void testMatchAllFilter() throws Exception {
    MagnitudeFilter magnitudeFilter = new MagnitudeFilter(0.0, 5.0);
    DepthFilter depthFilter = new DepthFilter(-100000.0, -10000.0);
    PhraseFilter phraseFilter = new PhraseFilter("a", ANY);
    MatchAllFilter matchAllFilter = new MatchAllFilter();
    matchAllFilter.addFilter(magnitudeFilter);
    matchAllFilter.addFilter(depthFilter);
    matchAllFilter.addFilter(phraseFilter);
    List<QuakeEntry> filteredList = client.filter(QuakeTestHelper.getQuakeEntries(SMALL), matchAllFilter);
//    filteredList.forEach(System.out::println);
    assertThat(filteredList.size(), is(2));
  }


  @Test
  public void testCreateMatchAllFilterAgain() throws Exception {
    MagnitudeFilter magnitudeFilter = new MagnitudeFilter(0.0, 5.0, "Magnitude");
    DistanceFilter distanceFilter = new DistanceFilter(new Location(55.7308, 9.1153), 3000000, "Distance");
    PhraseFilter phraseFilter = new PhraseFilter("e", ANY, "Phrase");
    MatchAllFilter matchAllFilter = new MatchAllFilter(new Filter[]{magnitudeFilter, distanceFilter, phraseFilter});
    List<QuakeEntry> filteredList = client.filter(QuakeTestHelper.getQuakeEntries(LARGE), matchAllFilter);
//    filteredList.forEach(System.out::println);
    System.out.println(matchAllFilter.getName());
    assertThat(filteredList.size(), is(17));
  }

  @Test
  public void testCreateMatchAllFilterAgainQuiz() throws Exception {
    MagnitudeFilter magnitudeFilter = new MagnitudeFilter(0.0, 3.0, "Magnitude");
    DistanceFilter distanceFilter = new DistanceFilter(new Location(36.1314, -95.9372), 10000000, "Distance");
    PhraseFilter phraseFilter = new PhraseFilter("Ca", ANY, "Phrase");
    MatchAllFilter matchAllFilter = new MatchAllFilter(new Filter[]{magnitudeFilter, distanceFilter, phraseFilter});
    List<QuakeEntry> filteredList = client.filter(QuakeTestHelper.getQuakeEntries(LARGE), matchAllFilter);
//    filteredList.forEach(System.out::println);
    System.out.println(matchAllFilter.getName());
    assertThat(filteredList.size(), is(616));
  }



}