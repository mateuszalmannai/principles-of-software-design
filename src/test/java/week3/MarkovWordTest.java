package week3;

import duke.FileResource;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MarkovWordTest {

  private static final String BASE_PATH = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/";
  private static final String ROMEO = BASE_PATH + "romeo.txt";
  private static final String CONFUCIUS = BASE_PATH + "confucius.txt";

  @Test
  public void testGetFollows() throws Exception {

    MarkovWord markovWord = new MarkovWord(1);
    String text = "this is just a test yes this is a simple test";
    markovWord.setTraining(text);
    String[] targetArray = "this".split("\\s");
    WordGram target = new WordGram(targetArray, 0, targetArray.length);
    List<String> follows = markovWord.getFollows(target);

    System.out.println(follows);
  }

  @Test
  public void testIndexOf() throws Exception {
    String[] textArray = "this is just a test yes this is a simple test".split("\\s");
    String[] targetArray = "this is".split("\\s");
    WordGram target = new WordGram(targetArray, 0, targetArray.length);
    MarkovWord markovWord = new MarkovWord(1);
    int index = markovWord.indexOf(textArray, target, 0);
    assertThat(index, is(0));
    index = markovWord.indexOf(textArray, target, 5);
    assertThat(index, is(6));
    index = markovWord.indexOf(textArray, target, 8);
    assertThat(index, is(-1));
//    System.out.println(markovWord.indexOf(textArray,target,0));
//    System.out.println(markovWord.indexOf(textArray,target,5));
//    System.out.println(markovWord.indexOf(textArray,target,8));
  }

  @Test
  public void testGetRandomText() throws Exception {
    MarkovWord markovWord = new MarkovWord(1);
    String text = "this is just a test yes this is a simple test i repeat this is a test just a test.";
    markovWord.setTraining(text);
    String[] myText = text.split("\\s");
    String randomText = markovWord.getRandomText(100);
    for (int i = 0; i < myText.length; i++) {
      System.out.println(myText[i]);
    }
    System.out.println(randomText);
  }

  @Test
  public void testProgrammingExcercise() throws Exception {
    MarkovWord markovWord = new MarkovWord(3);
    MarkovRunner runner = new MarkovRunner();
    runner.runModel(markovWord, new FileResource(CONFUCIUS).asString(), 50, 643);
  }

  @Test
  public void testProgrammingQuiz() throws Exception {
    MarkovWord markovWord = new MarkovWord(3);
    MarkovRunner runner = new MarkovRunner();
    runner.runModel(markovWord, new FileResource(CONFUCIUS).asString(), 50, 621);
  }

  @Test
  public void testProgrammingQuiz2() throws Exception {
    MarkovWord markovWord = new MarkovWord(5);
    MarkovRunner runner = new MarkovRunner();
    runner.runModel(markovWord, new FileResource(CONFUCIUS).asString(), 50, 844);
  }

  @Test
  public void testEfficientMarkovWordHashMap() throws Exception {
    EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
    efficientMarkovWord.setRandom(42);
    efficientMarkovWord.setTraining("this is a test yes this is really a test");
    efficientMarkovWord.getRandomText(50);
  }

  @Test
  public void testEfficientMarkovWordHashMapQuiz() throws Exception {
    EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(3);
    MarkovRunner runner = new MarkovRunner();
    runner.runModel(efficientMarkovWord, new FileResource(CONFUCIUS).asString(), 50, 371);
  }

  @Test
  public void testEfficientMarkovWordHashMapQuiz2() throws Exception {
    EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
    MarkovRunner runner = new MarkovRunner();
    runner.runModel(efficientMarkovWord, new FileResource(CONFUCIUS).asString(), 50, 65);
  }


  @Test
  public void testEfficientMarkovWordHashMap2() throws Exception {
    EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
    efficientMarkovWord.setRandom(42);
    efficientMarkovWord.setTraining("this is a test yes this is really a test yes a test this is wow");
    efficientMarkovWord.getRandomText(50);
  }


  @Test
  public void testCompareMethods() throws Exception {

    MarkovRunner runner = new MarkovRunner();
    String text = new FileResource(BASE_PATH + "hawthorne.txt").asString();
    long startTime = System.currentTimeMillis();
    runner.runModel(new MarkovWord(2), text, 100, 42);
    long endTime = System.currentTimeMillis();
    long markovWordDuration = (endTime - startTime);
    startTime = System.currentTimeMillis();
    runner.runModel(new EfficientMarkovWord(2), text, 100, 42);
    endTime = System.currentTimeMillis();
    long efficientMarkovWordDuration = (endTime - startTime);

    System.out.println("Duration for standard MarkovWord: " + markovWordDuration + "ms");
    System.out.println("Duration for EfficientMarkovWord: " + efficientMarkovWordDuration + "ms");
  }


}