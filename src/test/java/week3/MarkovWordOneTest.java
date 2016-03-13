package week3;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MarkovWordOneTest {

  private static final String BASE_PATH = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/";
  private static final String ROMEO = BASE_PATH + "romeo.txt";
  private static final String CONFUCIUS = BASE_PATH + "confucius.txt";
  private MarkovRunner runner;

  @Before
  public void setUp() throws Exception {
    runner = new MarkovRunner();
  }

  @Test
  public void testIndexOf() throws Exception {
    String source = "this is just a test yes this is a simple test";
    String[] words = source.split("\\s+");
    MarkovWordOne markovWordOne = new MarkovWordOne();
    int index = markovWordOne.indexOf(words, "this", 0);
    assertThat(index, is(0));
    index = markovWordOne.indexOf(words, "this", 3);
    assertThat(index, is(6));
    index = markovWordOne.indexOf(words, "frog", 0);
    assertThat(index, is(-1));
    index = markovWordOne.indexOf(words, "frog", 5);
    assertThat(index, is(-1));
    index = markovWordOne.indexOf(words, "simple", 2);
    assertThat(index, is(9));
    index = markovWordOne.indexOf(words, "test", 5);
    assertThat(index, is(10));
  }

  @Test
  public void testGetFollows() throws Exception {
    String source = "this is just a test yes this is a simple test";
    MarkovWordOne markovWordOne = new MarkovWordOne();
    runner.runModel(markovWordOne, source, 10);
  }

  @Test
  public void testMarkovWordOne() throws Exception {
    runner.runModel(new MarkovWordOne(), new FileResource(CONFUCIUS).asString(), 120, 175);
  }

  @Test
  public void testMarkovWordOneQuiz() throws Exception {
    runner.runModel(new MarkovWordOne(), new FileResource(CONFUCIUS).asString(), 50, 139);
  }



}