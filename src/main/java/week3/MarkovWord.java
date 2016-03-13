package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovWord implements IMarkovModel {

  private String[] myText;
  private Random myRandom;
  private int myOrder;


  public MarkovWord(int myOrder) {
    this.myOrder = myOrder;
    myRandom = new Random();
  }

  @Override
  public void setTraining(String text) {
    myText = text.split("\\s+");
  }

  @Override
  public String getRandomText(int numWords) {
    StringBuilder sb = new StringBuilder();
    int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
    WordGram kGram = new WordGram(myText,index,myOrder);
    sb.append(kGram.toString()).append(" ");
    for(int k=0; k < numWords-1; k++){
      List<String> follows = getFollows(kGram);
      if (follows.size() == 0) {
        break;
      }
      index = myRandom.nextInt(follows.size());
      String next = follows.get(index);
      sb.append(next).append(" ");
      kGram = kGram.shiftAdd(next);
    }
    return sb.toString().trim();
  }

  @Override
  public void setRandom(int seed) {
    myRandom = new Random(seed);
  }

  protected int indexOf(String[] words, WordGram target, int start) {
    for (int i = start; i < words.length - target.length(); i++) {
      if (words[i].equals(target.wordAt(0))) {
        boolean targetFound = true;
        for (int j = 1; j < target.length(); j++) {
          if (!words[i + j].equals(target.wordAt(j))) {
            targetFound = false;
            break;
          }
        }
        if (targetFound) {
          return i;
        }
      }
    }
    return -1;
  }

  protected List<String> getFollows(WordGram kGram) {
    List<String> follows = new ArrayList<>();
    int counter = 0;
    while (counter < myText.length - kGram.length()) {
      int foundKey = indexOf(myText, kGram, counter);
      if (foundKey == -1 || foundKey + kGram.length() >= myText.length - 1) {
        break;
      }
//      if (foundKey + kGram.length() >= myText.length - 1) {
//        break;
//      }
      String next = myText[foundKey + kGram.length()];
      follows.add(next);
      counter = foundKey + kGram.length();
    }
    return follows;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
