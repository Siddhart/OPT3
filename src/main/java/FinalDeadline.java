import java.util.Date;

public class FinalDeadline extends Deadline{

    public FinalDeadline(String name, String username, String teamName, Date deadline, boolean hasToRemind){
        this.name = name;
        this.username = username;
        this.teamName = teamName;
        this.deadline = deadline;
        this.hasToRemind = hasToRemind;

        this.reminderDays = 7;
    }

    @Override
    public String getDeadlineString() {
        int days = getDaysleft();

        if (days <= 7) {
            //red
            return String.format("\u001B[31m%d days - %s - %s\u001B[0m", days, getName(), getAssigned());
        } else if (days > 7 && days <= 14) {
            //yellow
            return String.format("\u001B[33m%d days - %s - %s\u001B[0m", days, getName(), getAssigned());
        } else if (days > 14 && days <= 31) {
            //green
            return String.format("\u001B[32m%d days - %s - %s\u001B[0m", days, getName(), getAssigned());
        }

        //white
        return String.format("%d days - %s - %s", days, getName(), getAssigned());
    }

    @Override
    public String getType() {
        return "FINAL";
    }
}
