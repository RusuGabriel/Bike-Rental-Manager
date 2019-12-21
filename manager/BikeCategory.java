package manager;

import java.util.StringTokenizer;

public class BikeCategory {
    private int primaryKey;
    private String category;

    public BikeCategory() {
        primaryKey = 0;
        category = null;
    }

    public BikeCategory(int pK, String category) {
        primaryKey = pK;
        StringTokenizer stringT = new StringTokenizer(category, "' ");
        this.category = stringT.nextToken();
    }

    public BikeCategory(String[] args) {
        primaryKey = Integer.parseInt(args[0]);
        category = args[1];
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        else {
            BikeCategory bC = (BikeCategory) obj;

            return bC.toString().equalsIgnoreCase(toString()) || bC.getCategory().equalsIgnoreCase(getCategory());
        }
    }

    @Override
    public String toString() {
        return "'" + category + "'";
    }
}
