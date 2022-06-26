import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DeadlineNotifyerTest {

    @Test
    public void NotifyerTest() {
        DeadlineNotifyer notifyer = new DeadlineNotifyer();

        //final deadline A=0 B=0 C=1
        Deadline test1 = new FinalDeadline("FINAL_deadline 1", "test", null);
        test1.setDeadline(new Date(new Date().getTime() + 86400000 * 2));
        test1.setHasToRemind(true);
        Assert.assertTrue(notifyer.sendTaskCheck(test1));

        //task deadline A=1 B=1 C=0
        Deadline test2 = new TaskDeadline("TASK_deadline 1", "test", null);
        test2.setDeadline(new Date(new Date().getTime() + 86400000 * 3));
        test2.setHasToRemind(false);
        Assert.assertTrue(notifyer.sendTaskCheck(test2));

        //task deadline A=0 B=1 C=0
        Deadline test3 = new TaskDeadline("TASK_deadline 1", "test", null);
        test2.setDeadline(new Date(new Date().getTime() + 86400000 * 1));
        test2.setHasToRemind(false);
        Assert.assertFalse(notifyer.sendTaskCheck(test3));
    }

    @Test
    public void deadlineRandvoorwaardenTest() {
        String ROOD = "16711680";
        String GEEL = "16772608";
        String GROEN = "65310";

        DeadlineNotifyer notifyer = new DeadlineNotifyer();

        //ROOD
        Deadline test1 = new TaskDeadline("TASK_deadline 1", "test", null);
        test1.setDeadline(new Date(new Date().getTime() + 86400000 * 0));
        test1.setHasToRemind(false);
        Assert.assertEquals(ROOD, notifyer.getEmbedColor(test1));

        Deadline test2 = new TaskDeadline("TASK_deadline 2", "test", null);
        test2.setDeadline(new Date(new Date().getTime() + 86400000 * 3));
        test2.setHasToRemind(false);
        Assert.assertEquals(ROOD, notifyer.getEmbedColor(test2));


        //GEEL
        Deadline test3 = new TaskDeadline("TASK_deadline 3", "test", null);
        test3.setDeadline( new Date(new Date().getTime() + 86400000 * 4));
        test3.setHasToRemind(false);
        Assert.assertEquals(GEEL, notifyer.getEmbedColor(test3));

        Deadline test4 = new TaskDeadline("TASK_deadline 4", "test", null);
        test4.setDeadline( new Date(new Date().getTime() + 86400000 * 6));
        test4.setHasToRemind(false);
        Assert.assertEquals(GEEL, notifyer.getEmbedColor(test4));

        Deadline test5 = new TaskDeadline("TASK_deadline 5", "test", null);
        test5.setDeadline(new Date(new Date().getTime() + 86400000 * 7));
        test5.setHasToRemind(false);
        Assert.assertEquals(GEEL, notifyer.getEmbedColor(test5));

        //GROEN
        Deadline test6 = new TaskDeadline("TASK_deadline 6", "test", null);
        test6.setDeadline(new Date(new Date().getTime() + 86400000 * 8));
        test6.setHasToRemind(false);
        Assert.assertEquals(GROEN, notifyer.getEmbedColor(test6));

        Deadline test7 = new TaskDeadline("TASK_deadline 7", "test", null);
        test7.setDeadline(new Date(new Date().getTime() + 86400000 * 13));
        test7.setHasToRemind(false);
        Assert.assertEquals(GROEN, notifyer.getEmbedColor(test7));

        Deadline test8 = new TaskDeadline("TASK_deadline 8", "test", null);
        test8.setDeadline(new Date(new Date().getTime() + 86400000 * 14));
        test8.setHasToRemind(false);
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
