package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {

  private String[] myText;
  private Random myRandom;

  public MarkovWordTwo() {
    myRandom = new Random();
  }

  @Override
  public void setTraining(String text) {
    myText = text.split("\\s+");
  }

  @Override
  public String getRandomText(int numWords) {
    StringBuilder sb = new StringBuilder();
    int index = myRandom.nextInt(myText.length-2);
    String key1 = myText[index];
    String key2 = myText[index + 1];
    sb.append(key1).append(" ").append(key2).append(" ");
    for (int i = 0; i < numWords; i++) {
      List<String> follows = getFollows(key1, key2);
//      System.out.println(key1 + " " + key2 + ":" + follows);
      if (follows.size() == 0) {
        break;
      }
      index = myRandom.nextInt(follows.size());
      String next = follows.get(index);
      sb.append(next).append(" ");
      key1 = key2;
      key2 = next;
    }
    return sb.toString().trim();
  }

  protected int indexOf(String[] words, String target1, String target2, int start) {

    for (int i = start; i < words.length - 1; i++) {
      if (words[i].equals(target1) && words[i + 1].equals(target2)) {
        return i + 1;
      }
    }
    return -1;
  }

  /**
   * There is a mistake in the method
   * Think about where you want to start searching after the follow
   * word for each keyword is found
   *
   * @param key1
   * @param key2
   * @return
   */
  private List<String> getFollows(String key1, String key2) {
    List<String> follows = new ArrayList<>();
    int pos = 0;
    while(pos < myText.length){
      int start = indexOf(myText, key1, key2, pos);
      if(start == -1 || start + 1 >= myText.length){break;}
      String next = myText[start+1];
      follows.add(next);
      pos = start+1;
    }
    return follows;
  }


  @Override
  public void setRandom(int seed) {
    myRandom = new Random(seed);
  }

  @Override
  public String toString() {
    return "MarkovWordTwo";
  }
}
