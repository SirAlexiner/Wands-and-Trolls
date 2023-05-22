/**
 * This is the module info for the WiNG application.
 *
 * @author Arthur Borger Thorkildsen
 * @since May 14. 2023
 */
module WandsAndTrolls {
    requires com.google.gson;
    requires java.logging;
    requires lombok;
    requires javafx.media;
    requires javafx.controls;
  requires javafx.web;

  exports no.ntnu.idatg2001.grp13.gui.stage to javafx.graphics;
    exports no.ntnu.idatg2001.grp13.gui.util.settings to com.google.gson;
}
