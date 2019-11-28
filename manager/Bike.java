package manager;

import com.sun.mail.imap.protocol.INTERNALDATE;

public class Bike {
    private int primaryKey;
    private String color;
    private int speed;
    private BikeCategory bikeCategory;
    private BikeBrand bikeBrand;

    public Bike()
    {
        primaryKey=0;
        color=null;
        speed=0;
        bikeCategory=null;
        bikeBrand = null;
    }

    public Bike(int pK, String color, int speed, BikeBrand brand, BikeCategory category) {
        primaryKey = pK;
        this.color = color;
        this.speed = speed;
        this.bikeBrand = brand;
        this.bikeCategory = category;
    }

    public Bike(String[] args) {
        this(Integer.parseInt(args[0]),args[1],Integer.parseInt(args[2]),new BikeBrand(Integer.parseInt(args[3]),args[4]),new BikeCategory(Integer.parseInt(args[5]),args[6]));
    }

    public int getSpeed() {
        return speed;
    }

    public String getColor() {
        return color;
    }

    public void setBrand(String brand) {
        bikeBrand.setBrand(brand);
    }

    public String getCategory() {
        return bikeCategory.getCategory();
    }

    public void setBikeBrand(BikeBrand bikeBrand) {
        this.bikeBrand = bikeBrand;
    }

    public void setBikeCategory(BikeCategory bikeCategory) {
        this.bikeCategory = bikeCategory;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getBrand() {
        return bikeBrand.getBrand();
    }

    public BikeBrand getBikeBrand() {
        return bikeBrand;
    }

    public BikeCategory getBikeCategory() {
        return bikeCategory;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(bikeBrand.getPrimaryKey()+",");
        result.append(bikeCategory.getPrimaryKey()+",");
        result.append("'"+color+"',");
        result.append(speed);
        return result.toString();
    }

    public String update() {
        StringBuilder result = new StringBuilder();
        result.append("Brand_ID = "+bikeBrand.getPrimaryKey()+",");
        result.append("Category_ID = "+bikeCategory.getPrimaryKey()+",");
        result.append("Color = '"+color+"',");
        result.append("Speeds = "+speed);
        return result.toString();
    }
}
