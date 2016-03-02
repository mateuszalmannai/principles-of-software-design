package week1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static week1.QuakeTestHelper.Size.SMALL;

public class ClosestQuakesTest {


  ClosestQuakes closestQuakes;

  @Before
  public void setUp() throws Exception {
    closestQuakes = new ClosestQuakes();
  }

  @Test
  public void testGetClosest() throws Exception {
    Location jakarta = new Location(-6.211, 106.845);
    List<QuakeEntry> entryList = closestQuakes.getClosest(QuakeTestHelper.getQuakeEntries(SMALL), jakarta, 3);
    entryList.forEach(System.out::println);
    assertThat(entryList.size(), is(3));
    System.out.println("Number found: " + entryList.size());
  }

  @Test
  public void testFindClosestQuakes() throws Exception {
    closestQuakes.findClosestQuakes();
  }
}