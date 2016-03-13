package week3;

import java.util.Random;

public class MarkovZeroTest extends AbstractMarkovModel {


  public MarkovZeroTest() {
    super(0);
    myRandom = new Random();
  }

  public void setTraining(String s) {
    myText = s.trim();
  }

  public String getRandomText(int numChars) {
    if (myText == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (int k = 0; k < numChars; k++) {
      int index = myRandom.nextInt(myText.length());
      sb.append(myText.charAt(index));
    }

    return sb.toString();
  }

  public String toString() {
    return "MarkovModel of order zero";
  }
}
