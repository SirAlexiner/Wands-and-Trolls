package no.ntnu.idatg2001.grp13.gui.util.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.scene.SettingsScene;
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
public class SettingsDao {
  private static final String FILE_PATH = "cfg/json/settings.json";


  /**
   * This function loads the game settings from a json file using the Gson library in Java.
   *
   * @return a {@link no.ntnu.idatg2001.grp13.gui.util.settings.Settings} object
   */
  public static Settings loadSettingsFromFile() {
    Gson gson = DaoLoader.getDaoJsonLoader(FILE_PATH);
    JsonObject json = null;
    try (Reader reader = new FileReader(FILE_PATH)) {
      json = gson.fromJson(reader, JsonObject.class);
    } catch (IOException e) {
      ErrorLogger.LOGGER.log(Level.SEVERE,
          String.format("Unable to load settings from JSON. %s", e));
    }
    Settings settings = gson.fromJson(json, Settings.class);
    if (settings != null) {
      return settings;
    } else {
      return SettingsScene.getSettings();
    }
  }

  /**
   * This function saves a list of customers to a JSON file using the Gson library in Java.
   */
  public static void saveSettings() {
    Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    String json = gson.toJson(SettingsScene.getSettings());

    try (Writer writer = new FileWriter(FILE_PATH)) {
      writer.write(json);
    } catch (IOException e) {
      ErrorLogger.LOGGER.log(Level.SEVERE, String.format("Unable to save settings to JSON: %s", e));
    }
  }
}
