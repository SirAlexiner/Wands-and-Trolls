package no.ntnu.idatg2001.grp13.gui.util;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * <p>DaoLoader class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class DaoLoader {

  /**
   * <p>getDaoJsonLoader.</p>
   *
   * @param path a {@link java.lang.String} object
   * @return a {@link com.google.gson.Gson} object
   */
  public static Gson getDaoJsonLoader(String path) {
    Gson gson = new Gson();
    File file = new File(path);

    if (file.exists()) {
      ErrorLogger.LOGGER.log(Level.FINE, "File already exists.");
    } else {
      try {
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
          boolean direCreated = parentDir.mkdirs();
          boolean fileCreated = file.createNewFile();
          if (direCreated && fileCreated) {
            ErrorLogger.LOGGER.log(Level.FINE, "File and directory created successfully");
          } else {
            ErrorLogger.LOGGER.log(Level.WARNING,
                "Failed to create File and directory successfully");
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return gson;
  }
}
