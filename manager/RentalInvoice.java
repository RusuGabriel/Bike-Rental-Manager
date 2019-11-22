package manager;

import java.util.Date;

public class RentalInvoice {
    private int rentID;
    private int brandID;
    private int userID;
    private String brandName;
    private String userFullName;
    private String startDate;
    private String endDate;
    private double charge;

    public RentalInvoice() {
    }

    public RentalInvoice(int rentID, int userID, String userFullName, int brandID, String brandName, String start, String end, String charge) {
        startDate = start;
        endDate = end;
        try {
            this.charge = Double.parseDouble(charge);
        } catch (Exception e) {
            this.charge = 0;
        }
        this.rentID = rentID;
        this.userID = userID;
        this.userFullName = userFullName;
        this.brandID = brandID;
        this.brandName = brandName;
    }

    public RentalInvoice(String[] args) {
        this(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), args[4], args[5], args[6], args[7]);
    }

    public String getBrandName() {
        return brandName;
    }

    public double getCharge() {
        return charge;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public int getUserID() {
        return userID;
    }

    public int getBrandID() {
        return brandID;
    }

    public int getRentID() {
        return rentID;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(userID + ",");
        result.append(brandID + ",");
        result.append("'" + startDate + "'");
        return result.toString();
    }

    public String update() {
        return "User_ID = " + userID + ",Bike_ID = " + BikeRepository.getKeyOf(brandName) + ",StartTime = '" + startDate + "' ,EndTime='" + endDate + "'";
    }
}
