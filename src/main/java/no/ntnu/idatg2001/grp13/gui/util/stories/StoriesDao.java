package no.ntnu.idatg2001.grp13.gui.util.stories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.util.DaoLoader;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * The SettingsDao class uses the Gson library in Java to load and save the game settings
 * to a JSON file.
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class StoriesDao {
  private static final String FILE_PATH = "cfg/json/stories.json";
  private static Stories stories;


  /**
   * This function loads the game settings from a json file using the Gson library in Java.
   *
   * @return a {@link java.util.List} object
   */
  public static List<StoryReference> loadStoryReferencesFromFile() {
    Gson gson = DaoLoader.getDaoJsonLoader(FILE_PATH);
    JsonObject json = null;
    try (Reader reader = new FileReader(FILE_PATH)) {
      json = gson.fromJson(reader, JsonObject.class);
    } catch (IOException e) {
      ErrorLogger.LOGGER.log(Level.SEVERE,
          String.format("Unable to load stories from JSON. %s", e));
    }
    stories = gson.fromJson(json, Stories.class);
    if (stories == null) {
      stories = new Stories();
    }
    return stories.getStoryReferences();
  }

  /**
   * This function saves a list of customers to a JSON file using the Gson library in Java.
   */
  public static void saveStoryReferences() {
    Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("storyReferences", gson.toJsonTree(stories.getStoryReferences()));

    try (Writer writer = new FileWriter(FILE_PATH)) {
      gson.toJson(jsonObject, writer);
    } catch (IOException e) {
      ErrorLogger.LOGGER.log(Level.SEVERE,
          String.format("Unable to save story references to JSON: %s", e));
    }
  }
}
