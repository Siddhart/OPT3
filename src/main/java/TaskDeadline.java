import java.util.Date;

public class TaskDeadline extends Deadline {

    public TaskDeadline(String name, String username, String teamName, Date deadline) {
        this.name = name;
        this.username = username;
        this.teamName = teamName;
        this.deadline = deadline;

        this.reminderDays = 3;
    }

    @Override
    public String getDeadlineString() {
        int days = getDaysleft();

        if (days <= 3) {
            //red
            return String.format("\u001B[31m%d days - %s - %s\u001B[0m", days, getName(), getAssigned());
        } else if (days > 3 && days <= 7) {
            //yellow
            return String.format("\u001B[33m%d days - %s - %s\u001B[0m", days, getName(), getAssigned());
        } else if (days > 7 && days < 14) {
            //green
            return String.format("\u001B[32m%d days - %s - %s\u001B[0m", days, getName(), getAssigned());
        }

        //white
        return String.format("%d days - %s - %s", days, getName(), getAssigned());
    }

    @Override
    public String getType() {
        return "TASK";
    }
}
