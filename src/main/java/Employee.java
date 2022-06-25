import java.util.ArrayList;

public class Employee {
    private Login login;

    private String name;
    private boolean manager;
    private String teamName;

    Notifyer notifyer;

    public Employee(String tag, boolean manager, String teamName) {

        login = Login.getInstance();

        if (login.isAuthenticated()) {
            this.name = tag;
            this.manager = manager;
            this.teamName = teamName;
            this.notifyer = new Notifyer(this.name);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isManager() {
        return manager;
    }

    public String getTeam() {
        ArrayList<Team> teams = Company.getTeams();

        for(Team team : teams){
            if(this.name.equals(team.getManagerTag())){
                return team.getTeamName();
            }
        }

        return null;
    }
}