import java.util.ArrayList;

public class DeadlineNotifyer {


    public DeadlineNotifyer(String employeeName) {
        ArrayList<Deadline> employeeDeadlines = Deadline.getUserDeadline(employeeName);

        for (Deadline deadline : employeeDeadlines) {
            //check if the amount of days before the reminder is equal to the amount of days left till the deadline
            Sender sender = new Sender();

            if (sendTaskCheck(deadline)) {
                sender.sendMessage(generateMessage(deadline, employeeName));
                deadline.remind();
            } else if (sendFinalCheck(deadline)) {
                sender.sendMessage(generateMessage(deadline, deadline.getTeamName()));
                deadline.remind();
            }
        }
    }

    public DeadlineNotifyer() {

    }

    public boolean sendTaskCheck(Deadline deadline) {
        return (deadline.getDaysleft() == deadline.reminderDays && deadline.getType().equals("TASK")) || deadline.getReminder();
    }

    public boolean sendFinalCheck(Deadline deadline) {
        return (deadline.getDaysleft() == deadline.reminderDays && deadline.getType().equals("FINAL")) || deadline.getReminder();
    }

    public String getEmbedColor(Deadline d) {
        int days = d.getDaysleft();
        String type = d.getType();

        if ("FINAL".equals(type)) {
            if (days <= 7) {
                return "16711680";//red
            } else if (days > 7 && days <= 14) {
                return "16772608";//yellow
            } else if (days > 14 && days <= 31) {
                return "65310";//green
            }
        } else if ("TASK".equals(type)) {
            if (days <= 3) {
                return "16711680";//red
            } else if (days > 3 && days <= 7) {
                return "16772608";//yellow
            } else if (days > 7 && days <= 14) {
                return "65310";//green
            }
        }
        return "16777215";
    }

    public boolean checkImportant(int days, boolean isTask, boolean isFinal, boolean didSend) {
        boolean priority = false;

        if (days <= 3) {
            priority = true;
        } else if (days > 3 && days <= 7 && !isFinal && isTask) {
            priority = true;
        } else if (days > 7 && days <= 14 && isFinal && !isTask) {
            priority = true;
        }

        if (didSend) {
            priority = false;
        }

        return priority;
    }

    private String generateMessage(Deadline d, String tagName) {

        boolean isTask = !d.getDeadlineUser().equals("");
        boolean isFinal = !d.getTeamName().equals("");

        String title = "";
        if (checkImportant(d.getDaysleft(), isTask, isFinal, d.getReminder())) {
            //bold message
             title = String.format("**%s** - You have **%d** day(s) left to **%s**", tagName, d.getDaysleft(), d.getName());
        }else{
            //normal message
            title = String.format("%s - You have %d day(s) left to %s", tagName, d.getDaysleft(), d.getName());

        }


        //Discord embed format
        return String.format("{\"embeds\": [{\"title\":\"%s\", \"color\": \"%s\"}]}", title, getEmbedColor(d));
    }
}
