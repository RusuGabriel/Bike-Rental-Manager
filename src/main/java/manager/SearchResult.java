package manager;

public class SearchResult {
    private String firstColumn;
    private String secondColumn;

    public SearchResult() {
    }

    public SearchResult(String firstColumn, String secondColumn) {
        this.secondColumn = secondColumn;
        this.firstColumn = firstColumn;
    }

    public SearchResult(String[] args) {
        this(args[0], args[1]);
    }

    public String getSecondColumn() {
        return secondColumn;
    }

    public void setSecondColumn(String secondColumn) {
        this.secondColumn = secondColumn;
    }

    public String getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(String firstColumn) {
        this.firstColumn = firstColumn;
    }
}
