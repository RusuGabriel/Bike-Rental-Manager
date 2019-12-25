package manager;

import java.util.ArrayList;

public class SearchResultRepository {
    private static SearchResultRepository instance;
    private ArrayList<SearchResult> data;

    private SearchResultRepository() {
        data = new ArrayList<SearchResult>();
    }

    public synchronized static SearchResultRepository getInstance() {
        if (instance == null)
            instance = new SearchResultRepository();
        return instance;
    }

    public ArrayList<SearchResult> getData() {
        return data;
    }
}
