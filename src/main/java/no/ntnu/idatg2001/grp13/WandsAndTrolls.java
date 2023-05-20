package no.ntnu.idatg2001.grp13;

import no.ntnu.idatg2001.grp13.stage.MainStage;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

public class WandsAndTrolls {
  public static void main(String[] args) {
    // create an ErrorLogger object
    // and configures the logger to write to a file called ErrorLog.log.
    ErrorLogger errorLogger = new ErrorLogger();
    errorLogger.configureLogger("ErrorLog.log", "./cfg/logs");
    MainStage.startGame(args);
  }
}
