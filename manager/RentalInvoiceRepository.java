package manager;

import java.util.ArrayList;

public class RentalInvoiceRepository {
    private static RentalInvoiceRepository instance;
    private ArrayList<RentalInvoice> data;

    private RentalInvoiceRepository() {
        data = new ArrayList<RentalInvoice>();
    }

    public static RentalInvoiceRepository getInstance() {
        if (instance == null)
            instance = new RentalInvoiceRepository();
        return instance;
    }

    public ArrayList<RentalInvoice> getData() {
        return data;
    }

    public void setData(ArrayList<RentalInvoice> data) {
        this.data = data;
    }
}
