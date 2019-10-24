package manager;

public class BikeBrand {
    private int primary_key;
    private String name;

    public BikeBrand() {

    }

    //    public BikeBrand(String[] args)
//    {
//
//    }
//
    public BikeBrand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
