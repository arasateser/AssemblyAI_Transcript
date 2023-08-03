import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class RestApiT {
    public static void main(String[] args) throws URISyntaxException {

        Transcript transcript = new Transcript();
        transcript.setAudio_url("https://www.youtube.com/watch?v=0wvBu014E5o");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);
        System.out.println(jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization","f6ec5f2cb1f74953bc66862545ee79cc")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

    }
}
