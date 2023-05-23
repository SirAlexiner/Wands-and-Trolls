package no.ntnu.idatg2001.grp13.gui.util;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.scene.SettingsScene;

@UtilityClass
public class StylizedBorderPane {

  public static BorderPane getBorderPane() {
    BorderPane root = new BorderPane();
    root.getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    Image background = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Window/Background_Purple.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);
    root.getChildren().add(0, backgroundView);

    return root;
  }
}
