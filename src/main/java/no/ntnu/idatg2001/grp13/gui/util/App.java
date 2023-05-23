package no.ntnu.idatg2001.grp13.gui.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * <p>App class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class App {
  @Getter
  private static String version = "";
  @Getter
  private static String build = "";

  static {
    Properties properties = new Properties();
    try (InputStream inputStream = App.class.getResourceAsStream("/Properties/app.properties")) {
      properties.load(inputStream);
      version = properties.getProperty("version");
      build = properties.getProperty("build");
    } catch (IOException e) {
      ErrorLogger.LOGGER.log(Level.WARNING, String.format("Failed to load app.properties: %s", e));
    }
  }
}
