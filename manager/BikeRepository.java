package manager;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class BikeRepository {
    private static BikeRepository instance;
    private ArrayList<Bike> data;

    private BikeRepository() {
        data = new ArrayList<Bike>();
    }

    public static BikeRepository getInstance() {
        if (instance == null)
            instance = new BikeRepository();
        return instance;
    }

    public ArrayList<Bike> getData() {
        return data;
    }

    public boolean contains(BikeCategory bC) {
        for (Bike bike : data)
            if (bike.getBikeCategory().equals(bC))
                return true;
        return false;
    }

    public boolean contains(BikeBrand bikeBrand) {
        for (Bike bike : data)
            if (bike.getBikeBrand().equals(bikeBrand))
                return true;
        return false;
    }
}
