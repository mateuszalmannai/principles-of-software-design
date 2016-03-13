package week3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WordGramTest {

  @Test
  public void testWordGram() throws Exception {
    String source = "this is a test this is a test this is a test of words";
    String[] words = source.split("\\s+");
    int size = 4;
    for (int i = 0; i <= words.length - size; i++) {
      WordGram wordGram = new WordGram(words, i, size);
      System.out.println(i + "\t" + wordGram.length() + "\t" + wordGram);
    }
  }

  @Test
  public void testWordGramEquals() throws Exception {
    String source = "this is a test this is a test this is a test of words";
    String[] words = source.split("\\s+");
    List<WordGram> list = new ArrayList<>();
    int size = 4;
    for (int index = 0; index <= words.length - size; index += 1) {
      WordGram wg = new WordGram(words, index, size);
      list.add(wg);
    }
    WordGram first = list.get(0);
    System.out.println("checking " + first);
    for (int k = 0; k < list.size(); k++) {
      //if (first == list.get(k)) {
      if (first.equals(list.get(k))) {
        System.out.println("matched at " + k + " " + list.get(k));
      }
    }
  }

  @Test
  public void testShiftAdd() throws Exception {
    String source = "this is a test this is a test this is a test of words";
    String[] words = source.split("\\s+");
    int size = 4;
    int index = 2;
    WordGram wg = new WordGram(words,index,size);

    System.out.println("Original:\t"+wg);
    System.out.println("Shifted:\t"+wg.shiftAdd("Shift"));
  }
}