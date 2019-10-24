package manager;

public class BikeCategory {
    private int primary_key;
    private String brandName;

    public BikeCategory() {
    }

    public BikeCategory(String args) {
        brandName = args;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
