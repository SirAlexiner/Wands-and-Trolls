package no.ntnu.idatg2001.grp13.gui.util.AI;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.util.App;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * <p>Api class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class Api {
  @Getter
  private static String apiKey = "";

  static {
    Properties properties = new Properties();
    try (InputStream inputStream = App.class.getResourceAsStream("/Properties/app.properties")) {
      properties.load(inputStream);
      apiKey = properties.getProperty("apiKey");
    } catch (IOException e) {
      ErrorLogger.LOGGER.log(Level.WARNING,
          String.format("Failed to load api.properties: %s", e));
    }
  }
}
