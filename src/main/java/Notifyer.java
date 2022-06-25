import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

public class Notifyer {

    public final String webhookURL = "https://discord.com/api/webhooks/988899255307628584/WldSWO_FFDeZ02MsO3mV_oKhmzd2YGTMVOU_AaCL6eVgjMzEtysWtbwF_KZPEiJMzM65";

    private String getEmbedColor(Deadline d) {
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
        } else {
            if (days <= 3) {
                return "16711680";//red
            } else if (days > 3 && days <= 7) {
                return "16772608";//yellow
            } else if (days > 7 && days < 14) {
                return "65310";//green
            }
        }
        return "16777215";
    }

    private String generateMessage(Deadline d, String employeeName) {
        String title = String.format("@%s - You have **%d** day(s) left to **%s**", employeeName, d.getDaysleft(), d.getName());

        //Discord embed format
        return String.format("{\"embeds\": [{\"title\":\"%s\", \"color\": \"%s\"}]}", title, getEmbedColor(d));
    }

    private void sendMessage(String employeeName, Deadline d){
        try {
            URL url = new URL(webhookURL);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Siddhart");
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            OutputStream stream = con.getOutputStream();

            //get generated message
            stream.write(generateMessage(d, employeeName).getBytes());
            stream.flush();
            stream.close();

            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public Notifyer(String employeeName) {
        ArrayList<Deadline> employeeDeadlines = Deadline.getUserDeadline(employeeName);

        for(Deadline deadline : employeeDeadlines){
            //check if the amount of days before the reminder is equal to the amount of days left till the deadline
            if(deadline.reminderDays == deadline.getDaysleft()){
                sendMessage(employeeName, deadline);
            }
        }
    }
}
