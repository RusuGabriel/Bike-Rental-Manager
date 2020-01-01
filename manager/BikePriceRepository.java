package manager;

import java.util.ArrayList;

public class BikePriceRepository {

    private static BikePriceRepository instance;
    private ArrayList<BikePrice> data;

    private BikePriceRepository() {
        data = new ArrayList<BikePrice>();
    }

    public static BikePriceRepository getInstance() {
        if (instance == null)
            instance = new BikePriceRepository();
        return instance;
    }

    public ArrayList<BikePrice> getData() {
        return data;
    }
}
