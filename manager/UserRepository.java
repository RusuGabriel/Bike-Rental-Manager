package manager;

import java.util.ArrayList;

public class UserRepository {
    private static UserRepository instance;
    private ArrayList<User> data;

    private UserRepository() {
        data = new ArrayList<User>();
    }

    public static UserRepository getInstance() {
        if (instance == null)
            instance = new UserRepository();
        return instance;
    }

    public static int getKeyOf(String userName) {
        for(User u:UserRepository.getInstance().getData())
                if((u.getFirstName()+" "+ u.getSecondName()).equals(userName))
                    return u.getPrimaryKey();
        return -1;
    }

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }
}
