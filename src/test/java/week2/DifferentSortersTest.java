package week2;

import duke.QuakeEntry;
import duke.QuakeTestHelper;
import duke.TitleAndDepthComparator;
import duke.TitleLastAndMagnitudeComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static duke.QuakeTestHelper.Size.DEC6_SAMPLE1;
import static duke.QuakeTestHelper.Size.DEC6_SAMPLE2;
import static duke.QuakeTestHelper.Size.LARGE;
import static org.junit.Assert.*;

public class DifferentSortersTest {

  private DifferentSorters sorters;

  @Before
  public void setUp() throws Exception {
    sorters = new DifferentSorters();
  }

  @Test
  public void testSortWithCompareTo() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(LARGE);
    Collections.sort(quakeEntries);
    quakeEntries.forEach(System.out::println);
    int quakeNumber = 10;
    System.out.println("Print quake entry in position " + quakeNumber);
    System.out.println(quakeEntries.get(quakeNumber));
  }

  @Test
  public void testSortWithCompareToQuiz() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(DEC6_SAMPLE2);
    Collections.sort(quakeEntries);
    quakeEntries.forEach(System.out::println);
    int quakeNumber = 600;
    System.out.println("Print quake entry in position " + quakeNumber);
    System.out.println(quakeEntries.get(quakeNumber));
  }

  @Test
  public void testSortByTitleAndDepthQuiz() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(DEC6_SAMPLE2);
    Collections.sort(quakeEntries, new TitleAndDepthComparator());
    quakeEntries.forEach(System.out::println);
    int quakeNumber = 500;
    System.out.println("Print quake entry in position " + quakeNumber);
    System.out.println(quakeEntries.get(quakeNumber));
  }


  @Test
  public void testSortByTitleAndDepth() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(LARGE);
    Collections.sort(quakeEntries, new TitleAndDepthComparator());
    quakeEntries.forEach(System.out::println);
    int quakeNumber = 10;
    System.out.println("Print quake entry in position " + quakeNumber);
    System.out.println(quakeEntries.get(quakeNumber));
  }

  @Test
  public void testSortByLastWordInTitleThenByMagnitudeQuiz() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(DEC6_SAMPLE2);
    Collections.sort(quakeEntries, new TitleLastAndMagnitudeComparator());
    quakeEntries.forEach(System.out::println);
    int quakeNumber = 500;
    System.out.println("Print quake entry in position " + quakeNumber);
    System.out.println(quakeEntries.get(quakeNumber));
  }

  @Test
  public void testSortByLastWordInTitleThenByMagnitude() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(LARGE);
    Collections.sort(quakeEntries, new TitleLastAndMagnitudeComparator());
    quakeEntries.forEach(System.out::println);
    int quakeNumber = 10;
    System.out.println("Print quake entry in position " + quakeNumber);
    System.out.println(quakeEntries.get(quakeNumber));
  }
}