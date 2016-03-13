package week3;

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {

  private String[] myText;
  private Random myRandom;
  private int myOrder;
  private Map<WordGram, List<String>> myMap;


  public EfficientMarkovWord(int myOrder) {
    this.myOrder = myOrder;
    myRandom = new Random();
  }

  public void printHashMapInfo() {
    int largest = 0;
    //print the hashmap
    for (WordGram wordGram : myMap.keySet()) {
      //System.out.println("Key: "+wordGram+"\tValue: "+myMap.get(wordGram));
      if (myMap.get(wordGram).size() > largest) {
        largest = myMap.get(wordGram).size();
      }
    }
    //print the number of keys in the hashmap
    System.out.println("Number of keys in hashmap: " + myMap.size());
    //print the size of the largest value in the hashmap
    System.out.println("Size of largest value in hashmap: " + largest);
    //print the keys that have the maximum value
    System.out.println("Keys that have maximum value: ");
    for (WordGram wordGram : myMap.keySet()) {
      if (myMap.get(wordGram).size() == largest) {
//        System.out.println(wordGram.toString());
      }
    }
  }

  private Map<WordGram, List<String>> buildMap() {
    Map<WordGram, List<String>> mappedWords = new HashMap<>();
    int counter = 0;
    //while the current location of the myText array is less than the array's length minus the order
    while (counter < myText.length - (myOrder - 1)) {
      //create a new wordgram object that starts at the current location of myText string array
      //  with a length of myOrder
      WordGram wordGram = new WordGram(myText, counter, myOrder);

      //if the wordgram string array, acting as the key in the hashmap, is not in the hashmap
      if (!mappedWords.containsKey(wordGram) && counter + myOrder < myText.length) {
        //add new entry in hashmap with key of wordgram and value of word that follows
        mappedWords.put(wordGram, new ArrayList<>(Arrays.asList(myText[counter + myOrder])));
        //System.out.println("Word that follows wordgram: "+myText[counter+myOrder]);
      }
      //if the wordgram string array is already in the hashmap
      else if (mappedWords.containsKey(wordGram) && counter + myOrder < myText.length) {
        //get entry and replace it with current value + value of word that follows
        List<String> currentValues = mappedWords.get(wordGram);
        currentValues.add(myText[counter + myOrder]);
        mappedWords.replace(wordGram, currentValues);
        //System.out.println("Words in wordgram: "+currentValues);
      }
      //if the wordgram string array is not in the hashmap and we're at the end of the myText array
      else if (!mappedWords.containsKey(wordGram) && counter + myOrder == myText.length) {
        //create new entry with key of wordgram and empty value set
        mappedWords.put(wordGram, new ArrayList<String>());
      }
      counter++;
    }
    return mappedWords;
  }


  @Override
  public void setTraining(String text) {
    myText = text.split("\\s+");
  }

  @Override
  public String getRandomText(int numWords) {
    myMap = buildMap();
    printHashMapInfo();
    StringBuilder sb = new StringBuilder();
    int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
    WordGram kGram = new WordGram(myText, index, myOrder);
    sb.append(kGram.toString()).append(" ");
    for (int k = 0; k < numWords - 1; k++) {
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
