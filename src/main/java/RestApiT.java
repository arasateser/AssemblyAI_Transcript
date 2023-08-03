import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApiT {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        Transcript transcript = new Transcript();
        transcript.setAudio_url("https://www.youtube.com/watch?v=0wvBu014E5o");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);
        System.out.println(jsonRequest);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript" + transcript.getId()))
                .header("Authorization","f6ec5f2cb1f74953bc66862545ee79cc")
                //.GET()
                .build();

        while(true) {
        HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpRequest.BodyPublishers.ofString());
            //System.out.println(getResponse.body());

            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            //System.out.println(transcript.getId());
            System.out.println(transcript.getStatus());

            if ("completed".equals(transcript.getStatus() || "error".equals(transcript.getStatus())))
                break;
            }
        Thread.sleep(1000);
    }
}
