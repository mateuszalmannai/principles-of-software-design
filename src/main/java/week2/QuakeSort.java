package week2;

import duke.QuakeEntry;

import java.util.ArrayList;
import java.util.List;

public class QuakeSort {

  private QuakeEntry getSmallestMagnitude(List<QuakeEntry> quakes) {
    QuakeEntry min = quakes.get(0);
    for (QuakeEntry q : quakes) {
      if (q.getMagnitude() < min.getMagnitude()) {
        min = q;
      }
    }
    return min;
  }

  public List<QuakeEntry> sortByMagnitude(List<QuakeEntry> in) {
    //out starts as empty ArrayList
    List<QuakeEntry> out = new ArrayList<QuakeEntry>();
    //As long as in is not empty
    while (!in.isEmpty()) {
      //Find smallest element in in (minElement)
      QuakeEntry minElement = getSmallestMagnitude(in);
      //Remove minElement from in
      in.remove(minElement);
      //Add minElement to out
      out.add(minElement);
    }
    //out is the answer
    return out;
  }

  public void selectSort(List<QuakeEntry> in) {
    for (int i = 0; i < in.size(); i++) {
      int minIdx = i;
      for (int j = i + 1; j < in.size(); j++) {
        if (in.get(j).getMagnitude() < in.get(minIdx).getMagnitude()) {
          minIdx = j;
        }
      }
      QuakeEntry temp = in.get(i);
      in.set(i, in.get(minIdx));
      in.set(minIdx, temp);
    }
  }

  private int getIndexOfSmallestMagnitude(List<QuakeEntry> quakes, int from) {
    int minIdx = from;
    for (int i = from; i < quakes.size(); i++) {
      if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
        minIdx = i;
      }
    }
    return minIdx;
  }

  public void sortInPlaceByMagnitude(List<QuakeEntry> in) {
    // count from 0 to < in.size()
    for (int i = 0; i < in.size(); i++) {
      // find the smallest quake
      int minIdx = getIndexOfSmallestMagnitude(in, i);
      // swap the ith quake with the minIdxth quake
      QuakeEntry qi = in.get(i);
      QuakeEntry qmin = in.get(minIdx);
      in.set(i, qmin);
      in.set(minIdx, qi);
    }
  }

  private int getIndexOfLargestDepth(List<QuakeEntry> quakeData, int from) {
    int maxIdx = from;
    for (int i = from + 1; i < quakeData.size(); i++) {
      if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
        maxIdx = i;
      }
    }
    return maxIdx;
  }

  public void sortByLargestDepth(List<QuakeEntry> in) {
    for (int i = 0; i <50; i++) {
      int maxIdx = getIndexOfLargestDepth(in, i);
      QuakeEntry qi = in.get(i);
      QuakeEntry qmax = in.get(maxIdx);
      in.set(i, qmax);
      in.set(maxIdx, qi);
      System.out.println("################## Printing Quakes after pass " + (i + 1));
      in.forEach(System.out::println);
    }
  }

  private void onePassBubbleSort(List<QuakeEntry> quakeData, int numSorted) {
    for (int i = 0; i < quakeData.size() - numSorted - 1  ; i++) {
      if (quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
        QuakeEntry quakeEntry = quakeData.get(i + 1);
        quakeData.set(i + 1, quakeData.get(i));
        quakeData.set(i, quakeEntry);
      }
    }
  }

  public void onePassIntegerBubbleSort(List<Integer> ints, int numSorted) {
    for (int i = 0; i < ints.size() - numSorted - 1  ; i++) {
      if (ints.get(i) > ints.get(i + 1)) {
        Integer temp = ints.get(i + 1);
        ints.set(i + 1, ints.get(i));
        ints.set(i, temp);
      }
    }
  }



  public void sortByMagnitudeWithBubbleSort(List<QuakeEntry> in) {

    for (int i = 0; i < in.size(); i++) {
      onePassBubbleSort(in, i);
      System.out.println("################## Printing Quakes after pass " + i);
      in.forEach(System.out::println);
    }
  }

  public boolean isSortedAscendingByMagnitude(List<QuakeEntry> quakes) {
    QuakeEntry minEntry = quakes.get(0);
    for (int i = 1; i < quakes.size(); i++) {
      if (quakes.get(i).getMagnitude() < minEntry.getMagnitude()) {
        return false;
      }
      minEntry = quakes.get(i);
    }
    return true;
  }

  public void sortByMagnitudeWithBubbleSortWithCheck(List<QuakeEntry> in) {
    for (int i = 0; i < in.size(); i++) {
      if (isSortedAscendingByMagnitude(in)) {
        break;
      }
      onePassBubbleSort(in, i);
      System.out.println("################## Printing Quakes after pass " + (i + 1));
      in.forEach(System.out::println);
    }
  }


  public void sortByMagnitudeWithCheck(List<QuakeEntry> in) {
    for (int i = 0; i < in.size(); i++) {
      int minIdx = getIndexOfSmallestMagnitude(in, i);
      QuakeEntry qi = in.get(i);
      QuakeEntry qmin = in.get(minIdx);
      in.set(i, qmin);
      in.set(minIdx, qi);
      System.out.println("################## Printing Quakes after pass " + (i + 1));
      in.forEach(System.out::println);
      if (isSortedAscendingByMagnitude(in)) {
        break;
      }
    }
  }

}
