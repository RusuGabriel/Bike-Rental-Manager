package manager;

public class Address {
    private String street;
    private Integer number;
    private String city;
    private String country;
    private String state;

    public Address(String country, String state, String city, String street, Integer number) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
