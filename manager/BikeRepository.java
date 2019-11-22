package manager;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static int getKeyOf(String bikeBrand) {
        for (Bike bike : BikeRepository.getInstance().getData())
            if (bike.getBikeBrand().getBrand().equals(bikeBrand))
                return bike.getPrimaryKey();
        return -1;

    }

    public static HashMap<String, Integer> getTypeStatistics() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Bike bike : BikeRepository.getInstance().getData())
            if (map.get(bike.getCategory()) == null)
                map.put(bike.getCategory(), 1);
            else map.put(bike.getCategory(), map.get(bike.getCategory()) + 1);
        return map;
    }
}
