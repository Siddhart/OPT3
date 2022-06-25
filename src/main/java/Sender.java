import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class Sender implements ISender{

    public String endpoint = "https://discord.com/api/webhooks/988899255307628584/WldSWO_FFDeZ02MsO3mV_oKhmzd2YGTMVOU_AaCL6eVgjMzEtysWtbwF_KZPEiJMzM65";

    @Override
    public void sendMessage(String generateMessage) {
        try {
            URL url = new URL(endpoint);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Siddhart");
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            OutputStream stream = con.getOutputStream();

            //get generated message
            stream.write(generateMessage.getBytes());
            stream.flush();
            stream.close();

            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}

