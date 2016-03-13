package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovWordOne implements IMarkovModel {

  private String[] myText;
  private Random myRandom;

  public MarkovWordOne() {
    myRandom = new Random();
  }

  @Override
  public void setTraining(String text) {
    myText = text.split("\\s+");
  }

  @Override
  public String getRandomText(int numWords) {
    StringBuilder sb = new StringBuilder();
    int index = myRandom.nextInt(myText.length - 1);
    String key = myText[index];
    sb.append(key);
    sb.append(" ");
    for (int i = 0; i < numWords - 1; i++) {
      List<String> follows = getFollows(key);
//      System.out.println("Key: " + key + " follows:" + follows);
      if (follows.size() == 0) {
        break;
      }
      index = myRandom.nextInt(follows.size());
      String next = follows.get(index);
      sb.append(next);
      sb.append(" ");
      key = next;
    }
    return sb.toString().trim();
  }

  protected int indexOf(String[] words, String target, int start) {

    for (int i = start; i < words.length; i++) {
      if (words[i].equals(target)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * There is a mistake in the method
   * Think about where you want to start searching after the follow
   * word for each keyword is found
   *
   * @param key
   * @return
   */
  private List<String> getFollows(String key) {
    List<String> follows = new ArrayList<>();
    int pos = 0;
    int index = indexOf(myText, key, pos);
    while (pos < myText.length && index != -1) {
      follows.add(myText[index + 1]);
      index = indexOf(myText, key, index + 1);
      pos = index + key.length();
    }
    return follows;
  }

  @Override
  public void setRandom(int seed) {
    myRandom = new Random(seed);
  }

  @Override
  public String toString() {
    return "MarkovWordOne";
  }
}
