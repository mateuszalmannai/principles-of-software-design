package week1;

public class DepthFilter implements Filter {

  private double minDepth, maxDepth;
  private String filterName;

  public DepthFilter(double minDepth, double maxDepth, String filterName) {
    this.minDepth = minDepth;
    this.maxDepth = maxDepth;
    this.filterName = filterName;
  }

  public DepthFilter(double minDepth, double maxDepth) {
    this.minDepth = minDepth;
    this.maxDepth = maxDepth;
  }

  @Override
  public boolean satisfies(QuakeEntry qe) {
    double depth = qe.getDepth();
    return depth >= minDepth && depth <= maxDepth;
  }

  @Override
  public String getName() {
    return filterName;
  }
}
