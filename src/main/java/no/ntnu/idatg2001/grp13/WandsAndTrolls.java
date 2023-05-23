package no.ntnu.idatg2001.grp13;

import no.ntnu.idatg2001.grp13.gui.stage.MainStage;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

public class WandsAndTrolls {
  public static void main(String[] args) {
    ErrorLogger errorLogger = new ErrorLogger();
    errorLogger.configureLogger("ErrorLog.log", "./cfg/logs");
    MainStage.startGame(args);
  }
}
