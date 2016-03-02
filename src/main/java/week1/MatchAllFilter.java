package week1;

import java.util.ArrayList;
import java.util.List;

public class MatchAllFilter implements Filter {

  private List<Filter> filterList;

  public MatchAllFilter() {
    filterList = new ArrayList<>();
  }

  public MatchAllFilter(Filter[] filters) {
    this();
    for (Filter filter : filters) {
      addFilter(filter);
    }
  }

  public void addFilter(Filter filter) {
    filterList.add(filter);
  }


  @Override
  public boolean satisfies(QuakeEntry qe) {
    boolean result = true;
    for (Filter filter : filterList) {
      if (!filter.satisfies(qe)) {
        result = false;
      }
    }
    return result;
  }

  @Override
  public String getName() {
    StringBuffer filterNames = new StringBuffer();
    for (Filter filter : filterList) {
      filterNames.append(" " + filter.getName());
    }
    return filterNames.toString();
  }
}
