package manager;

import java.util.ArrayList;

public class BikeBrandRepository {
    private static BikeBrandRepository instance;
    private ArrayList<BikeBrand> data;

    private BikeBrandRepository(){
        data = new ArrayList<BikeBrand>();
    }

    public static BikeBrandRepository getInstance() {
        if(instance==null)
            instance = new BikeBrandRepository();
        return instance;
    }

    public ArrayList<BikeBrand> getData() {
        return data;
    }

    public void setData(ArrayList<BikeBrand> data) {
        this.data = data;
    }
}
