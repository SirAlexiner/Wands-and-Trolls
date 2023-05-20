package no.ntnu.idatg2001.grp13.gui.elements;

import java.util.Objects;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.stage.MainStage;

public class FantasyButton extends Button {
  @Getter
  @Setter
  private ButtonType buttonType;

  public FantasyButton() {
  }

  public FantasyButton(String s) {
    Label label = new Label(s.toUpperCase());
    setGraphic(label);
    getStylesheets().add(String.valueOf(FantasyButton.class.getResource(
        "/CSS/WindowUi/FantasyButton.css")));
    setOnMouseEntered(event -> setCursor(Cursor.HAND));
    setOnMouseExited(event -> setCursor(Cursor.DEFAULT));
    setOnAction(event -> {
      AudioClip buttonClick = new AudioClip(
          Objects.requireNonNull(MainStage.class.getResource("/Audio/mouseclick_softer.wav"))
              .toString());
      buttonClick.play();
    });
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
