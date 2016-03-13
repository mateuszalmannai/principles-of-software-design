package week3;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MarkovWordTwoTest {

  private static final String BASE_PATH = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/";
  private static final String ROMEO = BASE_PATH + "romeo.txt";
  private static final String CONFUCIUS = BASE_PATH + "confucius.txt";

  private MarkovWordTwo markovWordTwo;
  private MarkovRunner runner;

  @Before
  public void setUp() throws Exception {
    runner = new MarkovRunner();
    markovWordTwo = new MarkovWordTwo();

  }

  @Test
  public void testIndexOf() throws Exception {
    String source = "this is just a test yes this is a simple test";
    String[] words = source.split("\\s+");
    int index = markovWordTwo.indexOf(words, "this", "is", 0);
    assertThat(index, is(1));
    index = markovWordTwo.indexOf(words, "this", "is", 3);
    assertThat(index, is(7));
    index = markovWordTwo.indexOf(words, "frog", "jump", 0);
    assertThat(index, is(-1));
    index = markovWordTwo.indexOf(words, "is", "a",  5);
    assertThat(index, is(8));
    index = markovWordTwo.indexOf(words, "simple", "test", 2);
    assertThat(index, is(10));
  }

  @Test
  public void testGetFollows() throws Exception {
    String source = "this is just a test yes this is a simple test";
    runner.runModel(markovWordTwo, source, 45);
  }

  @Test
  public void testMarkovWordTwo() throws Exception {
    runner.runModel(new MarkovWordTwo(), new FileResource(CONFUCIUS).asString(), 50, 549);
  }

  @Test
  public void testMarkovWordTwoQuiz() throws Exception {
    runner.runModel(new MarkovWordTwo(), new FileResource(CONFUCIUS).asString(), 50, 832);
  }
}