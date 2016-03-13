package week3;

public class WordGram {
  private String[] myWords;
  private int myHash;

  public WordGram(String[] source, int start, int size) {
    myWords = new String[size];
    System.arraycopy(source, start, myWords, 0, size);
  }

  public int hashCode() {
    if (myHash == 0) {
      myHash = this.toString().hashCode();
    }
    return myHash;
  }

  public String wordAt(int index) {
    if (index < 0 || index >= myWords.length) {
      throw new IndexOutOfBoundsException("bad index in wordAt " + index);
    }
    return myWords[index];
  }

  public int length() {
    return myWords.length;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < myWords.length; i++) {
      sb.append(myWords[i]);
      sb.append(" ");
    }

    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    WordGram other = (WordGram) o;
    if (length() != (other.length())) {
      return false;
    }
    for (int i = 0; i < myWords.length; i++) {
      if (!myWords[i].equals(other.wordAt(i))) {
        return false;
      }
    }

    return true;

  }

  // shift all words one towards 0 and add word at the end.
  // you lose the first word
  public WordGram shiftAdd(String word) {
    String[] newWords = new String[length()];
    for (int i = 0; i < newWords.length - 1; i++) {
      newWords[i] = wordAt(i + 1);
    }
    newWords[newWords.length - 1] = word;
    return new WordGram(newWords, 0, newWords.length);
  }

}