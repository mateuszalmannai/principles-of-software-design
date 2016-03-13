package week3;
/**
 * Write a description of class MarkovZero here.
 *
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;

/**
 * Key algorithm: finding all follow characters
 */
public class EfficientMarkovModel extends AbstractMarkovModel {

  private Map<String, List<String>> followsMap;

  public EfficientMarkovModel(int order) {
    super(order);
  }

  @Override
  protected List<String> getFollows(String key) {
    return followsMap.get(key);
  }

  @Override
  public void setTraining(String text) {
    super.setTraining(text);
    followsMap = buildMap();
    printHashMapInfo();
  }

  private Map<String, List<String>> buildMap() {
    Map<String, List<String>> mappedChars = new HashMap<>();
    int pos = 0;
    int textLength = myText.length();
    while (pos < textLength - (ORDER - 1)) {
      String key = myText.substring(pos, pos + ORDER);
      if (!mappedChars.containsKey(key) && pos + ORDER < textLength) {
        mappedChars.put(key, new ArrayList<>(Arrays.asList(
          myText.substring(pos + key.length(), pos + key.length() + 1))));
      } else if (mappedChars.containsKey(key) && pos + ORDER < textLength) {
        List<String> currentValues = mappedChars.get(key);
        currentValues.add(myText.substring(pos + key.length(), pos + key.length() + 1));
        mappedChars.replace(key, currentValues);
      } else if (pos + ORDER == textLength) {
        mappedChars.put(key, new ArrayList<>());
      }
      pos++;
    }

    return mappedChars;
  }


  public void printHashMapInfo() {
    System.out.printf("Map size:\t%d\nMax size:\t%d\n", followsMap.size(), longestFollowsSize());

//    System.out.println("Key total: " + followsMap.size());
//    if (followsMap.size() < 20) {
//      System.out.println(followsMap);
//    }
//
//    int largest = 0;
//    for (String key : followsMap.keySet()) {
//      int keySize = followsMap.get(key).size();
//      if (keySize > largest) {
//        largest = keySize;
//      }
//    }
//    System.out.println("Largest value in HashMap: " + largest);
//    List<String> keysWithMax = new ArrayList<>();
//    for (String key : followsMap.keySet()) {
//      if (followsMap.get(key).size() == largest) {
//        keysWithMax.add(key);
//      }
//    }
//    System.out.println("Keys with maximum size value: " + keysWithMax);
  }

  private int longestFollowsSize() {

    int maxSize = 0;
    for (String s : followsMap.keySet()) {
      maxSize = Math.max(maxSize, followsMap.get(s).size());
    }
    return maxSize;
  }


  @Override
  public String toString() {
    return "EfficientMarkovModel of order " + ORDER;
  }
}
