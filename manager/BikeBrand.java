package manager;

public class BikeBrand {
    private int primaryKey;
    private String brand;

    public BikeBrand() {
    }

    public BikeBrand(int pK, String brand) {
        primaryKey = pK;
        this.brand = brand;
    }


    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
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
            return bC.getBrand() == getBrand();
        }
    }

    @Override
    public String toString() {
        return "'"+brand+"'";
    }
}
