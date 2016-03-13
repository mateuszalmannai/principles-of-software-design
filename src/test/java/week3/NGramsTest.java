package week3;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NGramsTest {

  private static final String BASE_PATH = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/";
  private static final String ROMEO = BASE_PATH + "romeo.txt";
  private static final String CONFUCIUS = BASE_PATH + "confucius.txt";

  MarkovRunner runner;

  @Before
  public void setUp() throws Exception {
    runner = new MarkovRunner();
  }

  @Test
  public void testMarkovZero() throws Exception {
    MarkovZeroTest markovZeroTest = new MarkovZeroTest();
    markovZeroTest.setRandom(1024);
    runner.runMarkov(new FileResource(CONFUCIUS), markovZeroTest, 500);
  }
}