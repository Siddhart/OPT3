import java.util.Date;

public abstract class Deadline {
    //Template Method Pattern

    protected String name;
    protected String username;
    protected String teamName;
    protected Date deadline;

    protected String type;

    public int getDaysleft(){
        long ms1 = new Date().getTime();
        long ms2 = deadline.getTime();
        long timeDiff;

        if (ms1 >= ms2) {
            timeDiff = ms1 - ms2;
        } else if (ms1 <= ms2) {
            timeDiff = ms2 - ms1;
        } else {
            timeDiff = 0;
        }

        return (int) (timeDiff / (1000 * 60 * 60 * 24));
    }


    public String getDeadlineUser(){
        return this.username;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public String getName(){
        return this.name;
    }

    public String getAssigned(){
        return this.username != null ? this.username : this.teamName;
    }

    public abstract String getDeadlineString();
    public abstract String getType();
}
