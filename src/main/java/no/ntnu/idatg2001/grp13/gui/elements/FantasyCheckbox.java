package no.ntnu.idatg2001.grp13.gui.elements;

import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import no.ntnu.idatg2001.grp13.gui.scene.SettingsScene;
import no.ntnu.idatg2001.grp13.gui.util.sound.SoundEffectPlayer;

/**
 * <p>FantasyCheckbox class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class FantasyCheckbox extends CheckBox {
  /**
   * <p>Constructor for FantasyCheckbox.</p>
   */
  public FantasyCheckbox() {
    getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyCheckbox.css")));
    setOnMouseEntered(event -> setCursor(Cursor.HAND));
    setOnAction(event -> SoundEffectPlayer.playMouseClickSoundEffect());
  }
}
