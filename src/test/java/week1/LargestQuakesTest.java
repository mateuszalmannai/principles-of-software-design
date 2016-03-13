package week1;

import duke.QuakeEntry;
import duke.QuakeTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static duke.QuakeTestHelper.Size.LARGE;
import static duke.QuakeTestHelper.Size.SMALL;

public class LargestQuakesTest {

  private LargestQuakes largestQuakes;

  @Before
  public void setUp() throws Exception {
    largestQuakes = new LargestQuakes();
  }

  @Test
  public void testFindLargestQuakes() throws Exception {
    List<QuakeEntry> quakeEntries = QuakeTestHelper.getQuakeEntries(SMALL);
    int indexOfLargest = largestQuakes.indexOfLargest(quakeEntries);
    assertThat(indexOfLargest, is(3));
    assertThat(quakeEntries.get(3).getMagnitude(), is(5.5));
  }

  @Test
  public void testGetLargest() throws Exception {
    List<QuakeEntry> quakeEntries = largestQuakes.getLargest(QuakeTestHelper.getQuakeEntries(SMALL), 5);
    quakeEntries.forEach(System.out::println);
  }

  @Test
  public void testGetLargestQuiz() throws Exception {
    List<QuakeEntry> quakeEntries = largestQuakes.getLargest(QuakeTestHelper.getQuakeEntries(LARGE), 20);
    quakeEntries.forEach(System.out::println);
  }
}