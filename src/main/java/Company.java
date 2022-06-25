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
            add(new TaskDeadline("Get design approval", "Siddhart", null, new Date(new Date().getTime() + 86400000 * 3)));//add 3 days
            add(new TaskDeadline("Convert Figma design to HTML and CSS", "Siddhart", null, new Date(new Date().getTime() + 86400000 * 7)));//add 7 days
            //with team
            add(new TaskDeadline("Setup website hosting 1/2", "Siddhart", null, new Date(new Date().getTime() + 86400000 * 13)));//add 13 days

            //final deadline ONLY TEAM
            add(new FinalDeadline("Setup website hosting 2/2", null, "Development", new Date(new Date().getTime() + 86400000 * 7)));//add 7 days
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
