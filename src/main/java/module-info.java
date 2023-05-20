module WandsAndTrolls {
  requires lombok;
  requires javafx.graphics;
  requires javafx.media;
  requires javafx.controls;
  requires java.logging;
  requires com.google.gson;

  exports no.ntnu.idatg2001.grp13;
  exports no.ntnu.idatg2001.grp13.gui.util;
  exports no.ntnu.idatg2001.grp13.util;
  exports no.ntnu.idatg2001.grp13.gui.elements;
  exports no.ntnu.idatg2001.grp13.gui.scene;
}