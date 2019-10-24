package manager;

import java.util.ArrayList;

public class BikeCategoryRepository {
    private static BikeCategoryRepository instance;
    private ArrayList<BikeCategory> data;

    private BikeCategoryRepository()
    {
        data = new ArrayList<BikeCategory>();
    }

    public static BikeCategoryRepository getInstance() {
        if(instance==null)
            instance = new BikeCategoryRepository();
        return instance;
    }

    public ArrayList<BikeCategory> getData() {
        return data;
    }

    public void setData(ArrayList<BikeCategory> data) {
        this.data = data;
    }
}
