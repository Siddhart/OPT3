import java.util.ArrayList;
import java.util.Date;

public class Company {
    public static ArrayList<User> users = new ArrayList<>(){
        {
            add(new User("Siddhart", "1234"));
            add(new User("Admin", "admin"));
        }
    };

    public static ArrayList<Deadline> deadlines = new ArrayList<>(){
        {
            add(new TaskDeadline("Siddhart", null, "Get design approval", new Date(1654543552000L)));
            add(new TaskDeadline("Siddhart", null, "Convert Figma design to HTML and CSS", new Date(1656703552000L)));


            add(new FinalDeadline("Siddhart", null, "Setup website hosting", new Date(1657481152000L)));
        }
    };

    public static ArrayList<User> getUsers(){
        return users;
    }
}
