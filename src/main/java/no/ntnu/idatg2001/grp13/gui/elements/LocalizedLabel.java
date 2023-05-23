package no.ntnu.idatg2001.grp13.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import no.ntnu.idatg2001.grp13.gui.util.language.LanguageManager;

/**
 * <p>LocalizedLabel class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class LocalizedLabel extends Label {

  /**
   * <p>Constructor for LocalizedLabel.</p>
   *
   * @param resourceKey a {@link java.lang.String} object
   */
  public LocalizedLabel(String resourceKey) {
    setPadding(new Insets(2));
    setWrapText(true);
    textProperty().bind(LanguageManager.getStringProperty(resourceKey));
  }
}
