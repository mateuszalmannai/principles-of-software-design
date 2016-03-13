package week3;

import duke.FileResource;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MarkovRunnerTest {


  private static final String BASE_PATH = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/";
  private static final String ROMEO = BASE_PATH + "romeo.txt";
  private static final String CONFUCIUS = BASE_PATH + "confucius.txt";

  private MarkovRunner markovRunner;

  @Before
  public void setUp() throws Exception {
    markovRunner = new MarkovRunner();
  }

  @Test
  public void testMarkovZero() throws Exception {
    MarkovZero markovZero = new MarkovZero();
    markovZero.setRandom(88);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovZero, 500);
    System.out.println(markovZero);
  }

  @Test
  public void testMarkovZeroTest() throws Exception {
    MarkovZero markovZero = new MarkovZero();
    markovZero.setRandom(1024);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovZero, 500);
    System.out.println(markovZero);
  }

  @Test
  public void testMarkovOne() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    markovOne.setRandom(42);
    markovRunner.runMarkov(new FileResource(ROMEO), markovOne, 500);
  }

  @Test
  public void testGetFollows() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    markovOne.setTraining("this is a test yes this is a test.");
    List<String> follows = markovOne.getFollows("t");
    assertThat(follows.size(), is(6));
    assertThat(follows, is(Arrays.asList("h", "e", " ", "h", "e", ".")));
    follows = markovOne.getFollows("e");
    assertThat(follows.size(), is(3));
    assertThat(follows, is(Arrays.asList("s", "s", "s")));
    follows = markovOne.getFollows("es");
    assertThat(follows.size(), is(3));
    assertThat(follows, is(Arrays.asList("t", " ", "t")));
    follows = markovOne.getFollows(".");
    assertThat(follows.size(), is(0));
    follows = markovOne.getFollows("t.");
    assertThat(follows.size(), is(0));
  }

  @Test
  public void testGetFollowsWithFile() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    FileResource fileResource = new FileResource(CONFUCIUS);
    markovOne.setTraining(fileResource.asString());
    List<String> follows = markovOne.getFollows("t");
    assertThat(follows.size(), is(11548));
  }

  @Test
  public void testGetFollowsWithFileTest() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    FileResource fileResource = new FileResource(CONFUCIUS);
    markovOne.setTraining(fileResource.asString());
    List<String> follows = markovOne.getFollows("o");
    assertThat(follows.size(), is(10453));
  }

  @Test
  public void testGetFollowsWithFileTest2() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    FileResource fileResource = new FileResource(CONFUCIUS);
    markovOne.setTraining(fileResource.asString());
    List<String> follows = markovOne.getFollows("he");
    assertThat(follows.size(), is(3715));
  }

  @Test
  public void testGetFollowsWithFileQuiz() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    FileResource fileResource = new FileResource(BASE_PATH + "melville.txt");
    markovOne.setTraining(fileResource.asString());
    List<String> follows = markovOne.getFollows("o");
    assertThat(follows.size(), is(4694));
  }

  @Test
  public void testGetFollowsWithFileQuiz2() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    FileResource fileResource = new FileResource(BASE_PATH + "melville.txt");
    markovOne.setTraining(fileResource.asString());
    List<String> follows = markovOne.getFollows("th");
    assertThat(follows.size(), is(1490));
  }

  @Test
  public void testMarkovOneWithSeed() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    markovOne.setRandom(42);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovOne, 500);
  }

  @Test
  public void testMarkovOneWithSeedQuiz() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    markovOne.setRandom(273);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovOne, 500);
  }

  @Test
  public void testMarkovOneWithSeedTest() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    markovOne.setRandom(365);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovOne, 500);
  }

  @Test
  public void testMarkovFour() throws Exception {
    MarkovOne markovOne = new MarkovOne();
    markovOne.setRandom(25);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovOne, 500);
    System.out.println(markovOne);
  }

  @Test
  public void testMarkovModelFourTest() throws Exception {
    MarkovModel markovModel = new MarkovModel(4);
    markovModel.setRandom(715);
    markovRunner.runMarkov(new FileResource(ROMEO), markovModel, 500);
    System.out.println(markovModel);
  }

  @Test
  public void testMarkovModelSevenTest() throws Exception {
    MarkovModel markovModel = new MarkovModel(7);
    markovModel.setRandom(953);
    markovRunner.runMarkov(new FileResource(ROMEO), markovModel, 500);
    System.out.println(markovModel);
  }

  @Test
  public void testMarkovModel() throws Exception {
    MarkovModel markovModel = new MarkovModel(6);
    markovModel.setRandom(38);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovModel, 500);
    System.out.println(markovModel);
  }

  @Test
  public void testMarkovModelQuiz() throws Exception {
    MarkovModel markovModel = new MarkovModel(4);
    markovModel.setRandom(371);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovModel, 500);
  }

  @Test
  public void testMarkovModelQuiz2() throws Exception {
    MarkovModel markovModel = new MarkovModel(8);
    markovModel.setRandom(365);
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovModel, 500);
  }

  @Test
  public void testMarkovOrders() throws Exception {
    String text = new FileResource(ROMEO).asString();
    markovRunner.runModel(new MarkovZero(), text, 500, 42);
    markovRunner.runModel(new MarkovOne(), text, 500, 42);
    markovRunner.runModel(new MarkovModel(2), text, 500, 42);
    markovRunner.runModel(new MarkovModel(3), text, 500, 42);

  }

  @Test
  public void testHashMap() throws Exception {
    EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(2);
    markovRunner.runModel(efficientMarkovModel, "yes-this-is-a-thin-pretty-pink-thistle", 50, 42);
    efficientMarkovModel.printHashMapInfo();
  }

  @Test
  public void testHashMapTest() throws Exception {
    EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(6);
    markovRunner.runModel(efficientMarkovModel, new FileResource(CONFUCIUS).asString(), 50, 792);
    efficientMarkovModel.printHashMapInfo();
  }

  @Test
  public void testHashMapTest2() throws Exception {
    EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(5);
    markovRunner.runModel(efficientMarkovModel, new FileResource(CONFUCIUS).asString(), 50, 531);
    efficientMarkovModel.printHashMapInfo();
  }

  @Test
  public void testEfficientMarkovModelAgainstMarkovModel() throws Exception {
    MarkovModel markovModel = new MarkovModel(2);
    EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(2);

    String text = new FileResource(BASE_PATH + "hawthorne.txt").asString();

    long startTime = System.currentTimeMillis();
    markovRunner.runModel(markovModel, text, 1000, 42);
    long endTime = System.currentTimeMillis();
    long duration = (endTime - startTime);
    System.out.println("Duration for standard MarkovModel: " + duration + "ms");

    startTime = System.currentTimeMillis();
    markovRunner.runModel(efficientMarkovModel, text, 1000, 42);
    endTime = System.currentTimeMillis();
    duration = (endTime - startTime);
    System.out.println("Duration for EfficientModel: " + duration + "ms");
  }

  @Test
  public void testEfficientMarkovModelQuiz() throws Exception {
    EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(5);
    efficientMarkovModel.setTraining(new FileResource(BASE_PATH + "romeo.txt").asString());
    efficientMarkovModel.setRandom(792);
  }

  @Test
  public void testMarkovWordOne() throws Exception {
    MarkovWordTwo markovWordOne = new MarkovWordTwo();
    markovRunner.runMarkov(new FileResource(CONFUCIUS), markovWordOne, 200);
  }
}