import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Company {
    public static ArrayList<User> users = new ArrayList<>() {
        {
            add(new User("Siddhart", "1234"));
            add(new User("Dev1", "1234"));
            add(new User("DevAdmin", "admin"));
            add(new User("test", "test"));
        }
    };

    public static ArrayList<Team> teams = new ArrayList<>() {
        {
            add(new Team("Development", "DevAdmin", (new String[]{"Siddhart", "Dev1"})));
        }
    };


    public static ArrayList<Deadline> deadlines = new ArrayList<>() {
        {
            //without team
            Deadline deadline1 = new TaskDeadline("Get design approval", "Siddhart", null);
            deadline1.setDeadline(new Date(new Date().getTime() + 86400000 * 3));//add 3 days
            deadline1.setHasToRemind(true);
            add(deadline1);

            Deadline deadline2 = new TaskDeadline("Convert Figma design to HTML and CSS", "Siddhart", null);
            deadline2.setDeadline(new Date(new Date().getTime() + 86400000 * 7));//add 7 days
            deadline2.setHasToRemind(true);
            add(deadline2);

            //with team
            Deadline deadline3 = new TaskDeadline("Setup website hosting 1/2", "Siddhart", null);
            deadline3.setDeadline(new Date(new Date().getTime() + 86400000 * 13));//add 13 days
            deadline3.setHasToRemind(false);
            add(deadline3);

            //final deadline ONLY TEAM
            Deadline deadline4 = new FinalDeadline("Setup website hosting 2/2", null, "Development");//add 7 days
            deadline4.setDeadline(new Date(new Date().getTime() + 86400000 * 7));//add 7 days
            deadline4.setHasToRemind(true);

            add(deadline4);
        }
    };

    public static void addDeadline(Deadline d) {
        deadlines.add(d);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }

    public static Team getTeam(String teamName) {
        for (Team team : teams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }

        return null;
    }
}
