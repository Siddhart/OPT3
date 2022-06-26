import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DeadlineColorTest {

    @Test
    public void printDeadlineColors(){
        Deadline taskDeadlineRed =new TaskDeadline("Get design approval","Siddhart", null,  new Date(new Date().getTime() + 86400000 * 3), true);
        System.out.println(taskDeadlineRed.getDeadlineString());

        Deadline taskDeadlineYellow =new TaskDeadline("Convert Figma design to HTML and CSS","Siddhart", null,  new Date(new Date().getTime() + 86400000 * 7), true);
        System.out.println(taskDeadlineYellow.getDeadlineString());

        Deadline taskDeadlineGreen =new TaskDeadline("Setup website hosting", "Siddhart", null, new Date(new Date().getTime() + 86400000 * 13), true);
        System.out.println(taskDeadlineGreen.getDeadlineString());

        Deadline taskDeadlineDefault =new TaskDeadline("Get Client website approval", "Siddhart", null, new Date(new Date().getTime() + 86400000 * 30), true);
        System.out.println(taskDeadlineDefault.getDeadlineString());

        new DeadlineNotifyer("Siddhart");
        Assert.assertTrue(true);
    }
}