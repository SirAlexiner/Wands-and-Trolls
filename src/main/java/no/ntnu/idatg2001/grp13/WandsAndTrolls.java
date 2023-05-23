package no.ntnu.idatg2001.grp13;

import no.ntnu.idatg2001.grp13.gui.stage.MainStage;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * The class sets up an error logger and starts the game.
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class WandsAndTrolls {
  /**
   * The main function initializes an error logger and starts the game.
   *
   * @param args an array of {@link java.lang.String} objects
   */
  public static void main(String[] args) {
    ErrorLogger errorLogger = new ErrorLogger();
    errorLogger.configureLogger("ErrorLog.log", "./cfg/logs");
    MainStage.startGame(args);
  }
}
