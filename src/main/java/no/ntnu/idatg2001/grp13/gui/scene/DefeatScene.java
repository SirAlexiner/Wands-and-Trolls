package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.util.MusicPlayer;

@UtilityClass
public class DefeatScene {

  public static Scene getDefeatScene(Stage stage) {
    BorderPane root = new BorderPane();
    root.getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    Image background = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Window/Background_Red.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    Image victory = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Window/Defeat.png")));
    ImageView victoryView = new ImageView(victory);

    root.getChildren().add(backgroundView);
    root.setCenter(victoryView);

    root.sceneProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        Platform.runLater(MusicPlayer::playDefeatMusic);
      }
    });

    HBox goBackButtonBox = new HBox();
    goBackButtonBox.setAlignment(Pos.CENTER);
    goBackButtonBox.setPadding(new Insets(20));

    FantasyButton mainMenuButton = new FantasyButton("button.mainMenu");
    mainMenuButton.setFantasyButtonType(FantasyButtonType.BONE);
    mainMenuButton.setOnAction(event -> {
      MusicPlayer.stop();
      MainMenuScene.getContentContainer().getChildren().clear();
      MainMenuScene.getContentContainer().getChildren().add(MainMenuScene.getScene(stage));
      MusicPlayer.playMainMenuMusic();
    });

    goBackButtonBox.getChildren().add(mainMenuButton);

    root.setBottom(goBackButtonBox);

    return new Scene(root);
  }
}