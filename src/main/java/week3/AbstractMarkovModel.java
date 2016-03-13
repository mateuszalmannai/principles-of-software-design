package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {
  protected String myText;
  protected Random myRandom;
  protected static int ORDER;

  public AbstractMarkovModel(int order) {
    myRandom = new Random();
    ORDER = order;
  }

  /**
   * Set training text
   */
  @Override
  public void setTraining(String text) {
    myText = text;
  }

  /**
   * Make random numbers predictable by providing a seed
   */
  @Override
  public void setRandom(int seed) {
    myRandom = new Random(seed);
  }


  /**
   * Generate random text by choosing characters at random from
   * the training text
   */
  @Override
  public String getRandomText(int numChars) {
    if (myText == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int index = myRandom.nextInt(myText.length() - ORDER);
    String key = myText.substring(index, index + ORDER);
    sb.append(key);

    for (int i = 0; i < numChars - ORDER; i++) {
      List<String> follows = getFollows(key);
//      System.out.println("key " + key + " " + follows);
      if (follows.size() == 0) {
        break;
      }
      index = myRandom.nextInt(follows.size());
      String next = follows.get(index);
      sb.append(next);
      key = key.substring(1) + next;
    }
    return sb.toString();
  }

  /**
   * Returns a list of all characters that follow the key
   * Method finds all the characters from the private variable myText
   * that follow key and puts all these characters into a List which is returned
   * Implement efficient version with HashMap
   * - Avoid re-scanning for 'th' follows
   * - Still use existing code with no change!
   * - i.e. store and reuse characters
   * Suppose getRandomText sees the String "he" 50 times. Each time, it will
   * calculate the follow set again, which could take a long time, each time
   * if the training text is long.
   * @param key
   * @return
   */
  protected List<String> getFollows(String key) {
    List<String> follows = new ArrayList<>();
    int pos = 0;
    int length = myText.length();
    int keyLength = key.length();
    while (pos < length) {
      int index = myText.indexOf(key, pos);
      if (index == -1 || (index >= length - keyLength)) {
        break;
      }
      follows.add(String.valueOf(myText.charAt(index + keyLength)));
      pos = index + keyLength;
    }
    return follows;
  }

  @Override
  public String toString() {
    return "MarkovModel of order " + ORDER;
  }
}
