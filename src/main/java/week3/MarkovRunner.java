package week3;
/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

import duke.FileResource;

public class MarkovRunner {

  public void runMarkov(FileResource fileResource, IMarkovModel markov, int size) {
    String st = fileResource.asString();
    st = st.replace('\n', ' ');
//    String st = "this is a test yes a test";
    markov.setTraining(st);
    for (int k = 0; k < 3; k++) {
      String text = markov.getRandomText(size);
      printOut(text);
    }
  }


  public void runModel(IMarkovModel markov, String text, int size, int seed) {
    markov.setTraining(text);
    markov.setRandom(seed);
    System.out.println("Running with " + markov);
    for (int i = 0; i < 3; i++) {
      String randomText = markov.getRandomText(size);
      printOut(randomText);
    }
  }

  public void runModel(IMarkovModel markov, String text, int size){
    markov.setTraining(text);
    System.out.println("running with " + markov);
    for(int k=0; k < 3; k++){
      String st = markov.getRandomText(size);
      printOut(st);
    }
  }


  private void printOut(String s) {
    String[] words = s.split("\\s+");
    int psize = 0;
    System.out.println("----------------------------------");
    for (int k = 0; k < words.length; k++) {
      System.out.print(words[k] + " ");
      psize += words[k].length() + 1;
      if (psize > 60) {
        System.out.println();
        psize = 0;
      }
    }
    System.out.println("\n----------------------------------");
  }

}
