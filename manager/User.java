package manager;

public class User {
    private int primaryKey;
    private String firstName;
    private String secondName;
    private IdentityCard userCard;
    private Address address;

    public User() {
    }

    public User(int userID, String firstName, String secondName, IdentityCard card, Address address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.userCard = card;
        this.address = address;
        this.primaryKey = userID;
    }

    public User(String[] args) {
        this(Integer.parseInt(args[0]), args[1], args[2], new IdentityCard(args[3], args[4].charAt(0), args[5], Integer.parseInt(args[6])), new Address(args[7], args[8], args[9], args[10], Integer.parseInt(args[11])));
    }

    public IdentityCard getUserCard() {
        return userCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setUserCard(IdentityCard userCard) {
        this.userCard = userCard;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public String getCNP() {
        return userCard.getCNP();
    }

    public Integer getNumber() {
        return userCard.getNumber();
    }

    public String getSeries() {
        return userCard.getSeries();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public Integer getStreetNumber() {
        return address.getNumber();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getState() {
        return address.getState();
    }

    public void setState(String state) {
        address.setState(state);
    }

    public String getCountry() {
        return address.getCountry();
    }

    public void setCountry(String country) {
        address.setCountry(country);
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public String getSex() {
        return userCard.getSex() + "";
    }

    public void setSex(char sex) {
        userCard.setSex(sex);
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("'" + this.getFirstName() + "',");
        result.append("'" + this.getSecondName() + "',");
        result.append("'" + this.getCNP() + "',");
        result.append("'" + this.getSex() + "',");
        result.append("'" + this.getSeries() + "',");
        result.append(this.getNumber() + ",");
        result.append("'" + this.getCountry() + "',");
        result.append("'" + this.getCity() + "',");
        result.append("'" + this.getState() + "',");
        result.append("'" + this.getStreet() + "',");
        result.append(this.getStreetNumber().toString());

        System.out.println(result.toString());
        return result.toString();
    }

    public String update() {
        StringBuilder update = new StringBuilder();
        update.append("First_Name = '"+firstName+"',");
        update.append("Second_Name = '" + this.getSecondName() + "',");
        update.append("CNP = '" + this.getCNP() + "',");
        update.append("Sex = '" + this.getSex() + "',");
        update.append("IdentityCardSeries = '" + this.getSeries() + "',");
        update.append("IdentityCardNumber = "+this.getNumber() + ",");
        update.append("Country = '" + this.getCountry() + "',");
        update.append("City = '" + this.getCity() + "',");
        update.append("State = '" + this.getState() + "',");
        update.append("Street = '" + this.getStreet() + "',");
        update.append("StreetNumber ="+this.getStreetNumber().toString());

        return  update.toString();
    }
}
