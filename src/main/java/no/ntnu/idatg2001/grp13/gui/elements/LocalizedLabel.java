package no.ntnu.idatg2001.grp13.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import no.ntnu.idatg2001.grp13.gui.util.language.LanguageManager;

public class LocalizedLabel extends Label {

  public LocalizedLabel(String resourceKey) {
    setPadding(new Insets(2));
    setWrapText(true);
    textProperty().bind(LanguageManager.getStringProperty(resourceKey));
  }
}
