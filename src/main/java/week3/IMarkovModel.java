package week3;

public interface IMarkovModel {

  void setTraining(String text);

  String getRandomText(int numChars);

  void setRandom(int seed);
}
