import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DeadlineNotifyerTest {

    @Test
    public void NotifyerTest() {
        DeadlineNotifyer notifyer = new DeadlineNotifyer();

        //final deadline A=0 B=0 C=1
        Deadline test1 = new FinalDeadline("FINAL_deadline 1", "test", null, new Date(new Date().getTime() + 86400000 * 2), true);
        Assert.assertTrue(notifyer.sendTaskCheck(test1));

        //task deadline A=1 B=1 C=0
        Deadline test2 = new TaskDeadline("TASK_deadline 1", "test", null, new Date(new Date().getTime() + 86400000 * 3), false);
        Assert.assertTrue(notifyer.sendTaskCheck(test1));

        //task deadline A=0 B=1 C=0
        Deadline test3 = new TaskDeadline("TASK_deadline 1", "test", null, new Date(new Date().getTime() + 86400000 * 1), false);
        Assert.assertFalse(notifyer.sendTaskCheck(test3));
    }

    @Test
    public void deadlineRandvoorwaardenTest() {
        String ROOD = "16711680";
        String GEEL = "16772608";
        String GROEN = "65310";

        DeadlineNotifyer notifyer = new DeadlineNotifyer();

        //ROOD
        Deadline test1 = new TaskDeadline("TASK_deadline 1", "test", null, new Date(new Date().getTime() + 86400000 * 0), false);
        Deadline test2 = new TaskDeadline("TASK_deadline 2", "test", null, new Date(new Date().getTime() + 86400000 * 3), false);
        Assert.assertEquals(ROOD, notifyer.getEmbedColor(test1));
        Assert.assertEquals(ROOD, notifyer.getEmbedColor(test2));

        //GEEL
        Deadline test3 = new TaskDeadline("TASK_deadline 3", "test", null, new Date(new Date().getTime() + 86400000 * 4), false);
        Deadline test4 = new TaskDeadline("TASK_deadline 4", "test", null, new Date(new Date().getTime() + 86400000 * 6), false);
        Deadline test5 = new TaskDeadline("TASK_deadline 5", "test", null, new Date(new Date().getTime() + 86400000 * 7), false);
        Assert.assertEquals(GEEL, notifyer.getEmbedColor(test3));
        Assert.assertEquals(GEEL, notifyer.getEmbedColor(test4));
        Assert.assertEquals(GEEL, notifyer.getEmbedColor(test5));

        //GROEN
        Deadline test6 = new TaskDeadline("TASK_deadline 6", "test", null, new Date(new Date().getTime() + 86400000 * 8), false);
        Deadline test7 = new TaskDeadline("TASK_deadline 7", "test", null, new Date(new Date().getTime() + 86400000 * 13), false);
        Deadline test8 = new TaskDeadline("TASK_deadline 8", "test", null, new Date(new Date().getTime() + 86400000 * 14), false);
        Assert.assertEquals(GROEN, notifyer.getEmbedColor(test6));
        Assert.assertEquals(GROEN, notifyer.getEmbedColor(test7));
        Assert.assertEquals(GROEN, notifyer.getEmbedColor(test8));
    }

    @Test
    public void deadlineImporantTest() {
        DeadlineNotifyer notifyer = new DeadlineNotifyer();

        Assert.assertTrue(notifyer.checkImportant(0, true, false, false));
        Assert.assertFalse(notifyer.checkImportant(0, true, false, true));
        Assert.assertFalse(notifyer.checkImportant(0, false, true, true));
        Assert.assertTrue(notifyer.checkImportant(0, false, true, false));

        Assert.assertTrue(notifyer.checkImportant(7, true, false, false));
        Assert.assertFalse(notifyer.checkImportant(7, true, false, true));
        Assert.assertFalse(notifyer.checkImportant(7, false, true, true));
        Assert.assertFalse(notifyer.checkImportant(7, false, true, false));


        Assert.assertFalse(notifyer.checkImportant(14, true, false, false));
        Assert.assertFalse(notifyer.checkImportant(14, true, false, true));
        Assert.assertTrue(notifyer.checkImportant(14, false, true, false));
        Assert.assertFalse(notifyer.checkImportant(14, false, true, true));
    }
}
