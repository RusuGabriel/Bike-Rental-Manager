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

    public void setData(ArrayList<Bike> data) {
        this.data = data;
    }
}
