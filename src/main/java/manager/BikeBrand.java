package manager;

import java.util.StringTokenizer;

public class BikeBrand {
    private int primaryKey;
    private String brand;

    public BikeBrand() {
        primaryKey=0;
        brand=null;
    }

    public BikeBrand(int pK, String brand) {
        primaryKey = pK;

        StringTokenizer stringT = new StringTokenizer(brand,"' ");
        this.brand = brand;
    }


    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        else {
            BikeBrand bC = (BikeBrand) obj;
            return bC.getBrand().equals( getBrand());
        }
    }

    @Override
    public String toString() {
        return "'"+brand+"'";
    }
}
