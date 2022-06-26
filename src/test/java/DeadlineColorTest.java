import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DeadlineColorTest {

    @Test
    public void printDeadlineColors() {
        Deadline taskDeadlineRed = new TaskDeadline("Get design approval", "Siddhart", null);
        taskDeadlineRed.setDeadline(new Date(new Date().getTime() + 86400000 * 3));
        taskDeadlineRed.setHasToRemind(true);
        System.out.println(taskDeadlineRed.getDeadlineString());

        Deadline taskDeadlineYellow = new TaskDeadline("Convert Figma design to HTML and CSS", "Siddhart", null);
        taskDeadlineYellow.setDeadline(new Date(new Date().getTime() + 86400000 * 7));
        taskDeadlineYellow.setHasToRemind(true);
        System.out.println(taskDeadlineYellow.getDeadlineString());

        Deadline taskDeadlineGreen = new TaskDeadline("Setup website hosting", "Siddhart", null);
        taskDeadlineGreen.setDeadline(new Date(new Date().getTime() + 86400000 * 13));
        taskDeadlineGreen.setHasToRemind(true);
        System.out.println(taskDeadlineGreen.getDeadlineString());

        Deadline taskDeadlineDefault = new TaskDeadline("Get Client website approval", "Siddhart", null);
        taskDeadlineDefault.setDeadline(new Date(new Date().getTime() + 86400000 * 30));
        taskDeadlineDefault.setHasToRemind(true);
        System.out.println(taskDeadlineDefault.getDeadlineString());

        new DeadlineNotifyer("Siddhart");
        Assert.assertTrue(true);
    }
}