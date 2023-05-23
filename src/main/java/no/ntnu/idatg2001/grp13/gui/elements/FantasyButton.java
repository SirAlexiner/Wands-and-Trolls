package no.ntnu.idatg2001.grp13.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.util.language.LanguageManager;
import no.ntnu.idatg2001.grp13.gui.util.sound.SoundEffectPlayer;

public class FantasyButton extends Button {
  @Getter
  @Setter
  private ButtonType buttonType;

  public FantasyButton(String stringOrKey, boolean useResource) {
    Label label = new Label();
    if (useResource) {
      label.textProperty().bind(LanguageManager.getStringProperty(stringOrKey));
    } else {
      label.setText(stringOrKey);
    }
    label.setPadding(new Insets(0, 12, 0, 12));
    Tooltip buttonHoverText = new Tooltip(stringOrKey);
    if (useResource) {
      buttonHoverText.textProperty().bind(LanguageManager.getStringProperty(stringOrKey));
    }
    label.setTooltip(buttonHoverText);
    setGraphic(label);
    getStylesheets().add(String.valueOf(FantasyButton.class.getResource(
        "/CSS/WindowUi/FantasyButton.css")));
    setOnMouseEntered(event -> setCursor(Cursor.HAND));
    setOnMouseExited(event -> setCursor(Cursor.DEFAULT));
    setOnAction(event -> SoundEffectPlayer.playMouseClickSoundEffect());
  }

  public void setFantasyButtonType(FantasyButtonType buttonType) {
    switch (buttonType) {
      case BLUE -> getStylesheets().add(String.valueOf(FantasyButton.class.getResource(
          "/CSS/WindowUi/FantasyButton_Blue.css")));
      case BONE -> getStylesheets().add(String.valueOf(FantasyButton.class.getResource(
          "/CSS/WindowUi/FantasyButton_Bone.css")));
      default -> getStylesheets().add(String.valueOf(FantasyButton.class.getResource(
          "/CSS/WindowUi/FantasyButton.css")));
    }
  }
}
