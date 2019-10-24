package manager;

import java.util.Date;

public class RentalInvoice {
    private Date startDate;
    private Date endDate;
    private double charge;

    public RentalInvoice(Date start, Date end, double charge) {
        startDate = start;
        endDate = end;
        charge = charge;
    }

    public RentalInvoice(String[] args) {
        this(new Date(args[0]), new Date(args[1]), Double.parseDouble(args[2]));
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getCharge() {
        return charge;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }
}
