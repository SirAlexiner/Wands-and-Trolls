package no.ntnu.idatg2001.grp13.gui.elements;

import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.text.TextAlignment;
import no.ntnu.idatg2001.grp13.gui.scene.SettingsScene;

public class FantasyCheckbox extends CheckBox {
  public FantasyCheckbox() {
    getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyCheckbox.css")));
    setOnMouseEntered(event -> setCursor(Cursor.HAND));
  }
}
