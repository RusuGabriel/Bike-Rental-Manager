package manager;

public class BikePrice {
    private int pk;
    private String speed;
    private String brand;
    private String category;
    private String color;
    private String price;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public BikePrice() {
        pk = 0;
        brand = "";
        category = "";
        color = "";
        price = "";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BikePrice(int pk, String brand, String category, String color, String price) {
        this.pk = pk;
        this.brand = brand;
        this.category = category;
        this.color = color;
        this.price = price;
    }

    public BikePrice(String[] args) {
        this(Integer.parseInt(args[0]), args[1], args[2], args[3], args[4]);
    }
}
