import java.util.ArrayList;

public class Team {
    private String teamName;
    private String managerTag;
    private String[] teamMemberTags;

    public Team(String teamName, String managerTag, String[] teamMemberTags) {
        this.teamName = teamName;
        this.managerTag = managerTag;
        this.teamMemberTags = teamMemberTags;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getManagerTag() {
        return managerTag;
    }

    public String[] getTeamMemberTags() {
        return teamMemberTags;
    }
}
