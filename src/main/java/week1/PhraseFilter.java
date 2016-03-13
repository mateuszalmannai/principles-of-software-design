package week1;

import duke.QuakeEntry;
import week1.EarthQuakeClient.Where;

public class PhraseFilter implements Filter {

  private String searchPhrase;
  private Where where;
  private String filterName;

  public PhraseFilter(String searchPhrase, Where where, String filterName) {
    this.searchPhrase = searchPhrase;
    this.where = where;
    this.filterName = filterName;
  }

  public PhraseFilter(String searchPhrase, Where where) {
    this.searchPhrase = searchPhrase;
    this.where = where;
  }

  @Override
  public boolean satisfies(QuakeEntry qe) {
    String info = qe.getInfo();
    boolean result;

    switch (where) {
      case END:
        result = info.endsWith(searchPhrase);
        break;
      case START:
        result = info.startsWith(searchPhrase);
        break;
      default:
        result = info.contains(searchPhrase);
    }

    return result;
  }

  @Override
  public String getName() {
    return filterName;
  }


}
