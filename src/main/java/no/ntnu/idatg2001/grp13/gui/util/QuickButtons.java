package no.ntnu.idatg2001.grp13.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.scene.MainMenuScene;

/**
 * <p>QuickButtons class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class QuickButtons {
  /**
   * <p>getGoBackButton.</p>
   *
   * @param stage a {@link javafx.stage.Stage} object
   * @return a {@link no.ntnu.idatg2001.grp13.gui.elements.FantasyButton} object
   */
  public static FantasyButton getGoBackButton(Stage stage) {
    FantasyButton cancelButton = new FantasyButton("button.goBack", true);
    cancelButton.setFantasyButtonType(FantasyButtonType.BONE);
    cancelButton.setPrefWidth(200);
    cancelButton.setOnMouseClicked(event -> {
      FantasyAlert quitAlert = new FantasyAlert(stage);
      quitAlert.setTitle("alert.goBack");
      quitAlert.setAlertType(Alert.AlertType.CONFIRMATION);
      quitAlert.setHeader("alert.goBackText");

      quitAlert.showAndWait();

      if (FantasyAlert.getResult().equals(ButtonType.OK)) {
        MainMenuScene.getContentContainer().getChildren().remove(1);
      }
    });
    return cancelButton;
  }
}
