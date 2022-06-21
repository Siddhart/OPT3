import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Deadline {

    protected String name;
    protected String username;
    protected String teamName;
    protected Date deadline;

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

    public String getDeadlineTeam(){
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
