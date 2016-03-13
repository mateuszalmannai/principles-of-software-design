package week2;

import duke.DistanceComparator;
import duke.Location;
import duke.QuakeEntry;
import duke.QuakeTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static duke.QuakeTestHelper.Size.*;

public class QuakeSortTest {

  private QuakeSort quakeSort;

  @Before
  public void setUp() throws Exception {
    quakeSort = new QuakeSort();
  }

  @Test
  public void testSortByMagnitude() throws Exception {
    List<QuakeEntry> quakeEntryList = QuakeTestHelper.getQuakeEntries(SMALL);
    List<QuakeEntry> quakeEntries = quakeSort.sortByMagnitude(quakeEntryList);
    quakeEntries.forEach(System.out::println);
  }

  @Test
  public void testSortInPlaceByMagnitude() throws Exception {
    List<QuakeEntry> quakeEntryList = QuakeTestHelper.getQuakeEntries(LARGE);
    quakeSort.sortInPlaceByMagnitude(quakeEntryList);
    quakeEntryList.forEach(System.out::println);
  }

  @Test
  public void testSortByLargestDepthQuiz() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(DEC6_SAMPLE2);
    quakeSort.sortByLargestDepth(quakeEntries);
    quakeEntries.forEach(System.out::println);
  }

  @Test
  public void testSortByLargestDepth() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(SMALL);
    quakeSort.sortByLargestDepth(quakeEntries);
    quakeEntries.forEach(System.out::println);
  }

  @Test
  public void testSortByMagnitudeWithBubbleSort() throws Exception {
    List<QuakeEntry> quakeEntryList = QuakeTestHelper.getQuakeEntries(SAMPLE_SIX2);
    quakeSort.sortByMagnitudeWithBubbleSort(quakeEntryList);
  }

  @Test
  public void testSortByMagnitudeWithBubbleSortWithCheck() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(DEC6_SAMPLE2);
    quakeEntries.forEach(System.out::println);
    quakeSort.sortByMagnitudeWithBubbleSortWithCheck(quakeEntries);
  }

  @Test
  public void testSortByMagnitudeWithCheck() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(DEC6_SAMPLE2);
    quakeEntries.forEach(System.out::println);
    quakeSort.sortByMagnitudeWithCheck(quakeEntries);
  }

  @Test
  public void testQuakeSortDemo() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(LARGE);
    Collections.sort(quakeEntries);
    quakeEntries.forEach(System.out::println);
  }

  @Test
  public void testDistanceSort() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(LARGE);
    Location where = new Location(35.9886, -78.9072);
    Collections.sort(quakeEntries, new DistanceComparator(where));
    quakeEntries.forEach(System.out::println);
  }

  @Test
  public void testIntBubbleSort() throws Exception {
    List<Integer> integers = Arrays.asList(4, 2, 5, 9, 8, 1);
    System.out.println("Before sort: " + integers);
    for (int i = 0; i < integers.size(); i++) {
      quakeSort.onePassIntegerBubbleSort(integers, i);
      System.out.println("After pass #" + (i + 1) + ": " + integers);
    }

  }
}