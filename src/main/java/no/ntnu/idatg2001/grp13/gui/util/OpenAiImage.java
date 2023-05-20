package no.ntnu.idatg2001.grp13.gui.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javafx.scene.image.Image;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OpenAiImage {

  private static final String DALL_E_URL = "https://api.openai.com/v1/images/generations";

  public Image generateImage(String description) throws IOException, URISyntaxException {
    String apiKey = Api.getApiKey();
    URL url = new URI(DALL_E_URL).toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Authorization", "Bearer " + apiKey);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true);

    String requestBody =
        "{\"model\": \"image-alpha-001\", \"prompt\": \"Fantasy, Digital Art, Realistic, "
            + description
            + "\", \"num_images\":1, \"size\":\"1024x1024\", \"response_format\":\"url\"}";

    OutputStream outputStream = connection.getOutputStream();
    outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
    outputStream.flush();
    outputStream.close();

    int responseCode = connection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder jsonResponse = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        jsonResponse.append(line);
      }
      reader.close();

      Gson gson = new Gson();
      JsonObject jsonObject = gson.fromJson(jsonResponse.toString(), JsonObject.class);
      String imageUrl = jsonObject.getAsJsonArray("data")
          .get(0)
          .getAsJsonObject()
          .get("url")
          .getAsString();

      return new Image(imageUrl);
    } else {
      throw new IOException("Request failed with response code: " + responseCode);
    }
  }
}
