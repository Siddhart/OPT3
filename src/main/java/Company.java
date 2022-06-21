import java.util.ArrayList;

public class Company {
    public static ArrayList<User> users = new ArrayList<>(){
        {
            add(new User("siddhart", "1234"));
            add(new User("admin", "admin"));
        }
    };

    public static ArrayList<User> getUsers(){
        return users;
    }
}
