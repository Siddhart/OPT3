import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class Notifyer {

    private String getColor(int days, String type) {
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

    private String generateMessage(Deadline d, String type){
        String returnString = "";
        String title = String.format("You have **%d** day(s) left to **%s**", d.getDaysleft(), d.getName());

        returnString += "{\"embeds\": [{"
                + "\"title\": \"" + title + "\","
                + "\"color\": " + getColor(d.getDaysleft(),type)
                + "}]}";

        return returnString;
    }

    public Notifyer(String user, Deadline d, String type) {
        // CONFIG
        String tokenWebhook = "https://discord.com/api/webhooks/988899255307628584/WldSWO_FFDeZ02MsO3mV_oKhmzd2YGTMVOU_AaCL6eVgjMzEtysWtbwF_KZPEiJMzM65";

        try {
            URL url = new URL(tokenWebhook);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Siddhart");
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            OutputStream stream = con.getOutputStream();

            stream.write(generateMessage(d, type).getBytes());
            stream.flush();
            stream.close();

            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
